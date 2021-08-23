package kz.altynbekov.stockexchange.service;

import kz.altynbekov.stockexchange.io.StockFileReader;
import kz.altynbekov.stockexchange.model.ClientBalance;
import kz.altynbekov.stockexchange.model.Operation;
import kz.altynbekov.stockexchange.parsing.OperationDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StockService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockService.class);
    private final MarketService marketService;

    public StockService() {
        this.marketService = new MarketService();
    }

    public void processOperations(List<ClientBalance> clientBalanceList, String fileName) {
        OperationDeserializer operationDeserializer = new OperationDeserializer();
        try (InputStream fileInputStream = StockFileReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Operation currentOperation = operationDeserializer.deserialize(line);
                Optional<Operation> oppositeOperationOptional = marketService.getOppositeOperation(currentOperation);
                if (Objects.nonNull(oppositeOperationOptional) && oppositeOperationOptional.isPresent()) {
                    ClientBalance currentClientBalance = findBalanceByClientName(clientBalanceList, currentOperation.getClientName());
                    processOperation(currentOperation, currentClientBalance);

                    ClientBalance clientBalance = findBalanceByClientName(clientBalanceList, oppositeOperationOptional.get().getClientName());
                    processOperation(oppositeOperationOptional.get(), clientBalance);
                    marketService.finishOperation(oppositeOperationOptional.get());
                } else {
                    marketService.addOperation(currentOperation);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("File with name {} not found", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problems with reading file with name {}", fileName);
        }
    }

    public void processOperation(Operation operation, ClientBalance currentClientBalance) {
        int buySellCoeff = 1;
        if (isItSell(operation)) {
            buySellCoeff = -1;
        }
        switch (operation.getNoteType()) {
            case "A":
                currentClientBalance.setNoteACount(currentClientBalance.getNoteACount() + operation.getNoteCount() * buySellCoeff);
                break;
            case "B":
                currentClientBalance.setNoteBCount(currentClientBalance.getNoteBCount() + operation.getNoteCount() * buySellCoeff);
                break;
            case "C":
                currentClientBalance.setNoteCCount(currentClientBalance.getNoteCCount() + operation.getNoteCount() * buySellCoeff);
                break;
            case "D":
                currentClientBalance.setNoteDCount(currentClientBalance.getNoteDCount() + operation.getNoteCount() * buySellCoeff);
                break;
            default:
                throw new IllegalArgumentException();
        }
        currentClientBalance.setBalance(currentClientBalance.getBalance() - operation.getNoteCount() * operation.getNotePrice() * buySellCoeff);
    }

    private ClientBalance findBalanceByClientName(List<ClientBalance> clientBalanceList, String clientName) {
        return clientBalanceList.stream()
                .filter(clientBalance -> clientName.equals(clientBalance.getClientName()))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean isItSell(Operation operation) {
        return "s".equals(operation.getOperationType());
    }
}
