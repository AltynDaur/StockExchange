package kz.altynbekov.stockexchange.main;

import kz.altynbekov.stockexchange.io.StockFileWriter;
import kz.altynbekov.stockexchange.model.ClientBalance;
import kz.altynbekov.stockexchange.model.Operation;
import kz.altynbekov.stockexchange.io.StockFileReader;
import kz.altynbekov.stockexchange.parsing.OperationDeserializer;
import kz.altynbekov.stockexchange.service.MarketService;
import kz.altynbekov.stockexchange.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static final String ORDERS_TXT = "orders.txt";
    private static final String CLIENTS_TXT = "clients.txt";
    private static final String RESULT_TXT = "result.txt";

    public static void main(String[] args) {
        StockService stockService = new StockService();
        StockFileReader stockFileReader = new StockFileReader();
        StockFileWriter fileWriter = new StockFileWriter();

        List<ClientBalance> clientBalanceList = stockFileReader.readClientBalances(CLIENTS_TXT);

        stockService.processOperations(clientBalanceList, ORDERS_TXT);

        fileWriter.writeResults(clientBalanceList, RESULT_TXT);
    }


}
