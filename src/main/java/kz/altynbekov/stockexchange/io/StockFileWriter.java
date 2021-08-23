package kz.altynbekov.stockexchange.io;

import kz.altynbekov.stockexchange.model.ClientBalance;
import kz.altynbekov.stockexchange.parsing.ClientBalanceSerializer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StockFileWriter {

    public void writeResults(List<ClientBalance> clientBalanceList, String fileName) {

        ClientBalanceSerializer balanceSerializer = new ClientBalanceSerializer();
        List<String> lines = new ArrayList<>();
        for (ClientBalance clientBalance : clientBalanceList) {
            lines.add(balanceSerializer.serialize(clientBalance));
        }

        Path file = Paths.get(fileName);
        try {
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
