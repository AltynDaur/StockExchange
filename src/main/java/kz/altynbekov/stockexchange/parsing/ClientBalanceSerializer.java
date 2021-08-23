package kz.altynbekov.stockexchange.parsing;

import kz.altynbekov.stockexchange.model.ClientBalance;

public class ClientBalanceSerializer {

    public static final String DELIMITER = "\t";

    public String serialize(ClientBalance clientBalance) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(clientBalance.getClientName());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(clientBalance.getBalance());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(clientBalance.getNoteACount());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(clientBalance.getNoteBCount());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(clientBalance.getNoteCCount());
        stringBuilder.append(DELIMITER);
        stringBuilder.append(clientBalance.getNoteDCount());
        return stringBuilder.toString();
    }
}
