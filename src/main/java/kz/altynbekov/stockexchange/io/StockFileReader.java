package kz.altynbekov.stockexchange.io;

import kz.altynbekov.stockexchange.model.ClientBalance;
import kz.altynbekov.stockexchange.model.Operation;
import kz.altynbekov.stockexchange.parsing.ClientBalanceDeserializer;
import kz.altynbekov.stockexchange.parsing.OperationDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockFileReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(StockFileReader.class);

    public List<ClientBalance> readClientBalances(String fileName) {
        List<ClientBalance> clientBalanceList = new ArrayList<>();
        try (InputStream fileInputStream = StockFileReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream), StandardCharsets.UTF_8))) {
            reader.lines().forEach(line -> clientBalanceList.add(new ClientBalanceDeserializer().deserialize(line)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("File with name {} not found", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problems with reading file with name {}", fileName);
        }

        return clientBalanceList;
    }

    public List<Operation> readOperations(String fileName) {

        List<Operation> operations = new ArrayList<>();
        try (InputStream fileInputStream = StockFileReader.class.getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(fileInputStream), StandardCharsets.UTF_8))) {
                reader.lines().forEach(line -> operations.add(new OperationDeserializer().deserialize(line)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("File with name {} not found", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Problems with reading file with name {}", fileName);
        }

        return operations;
    }
}
