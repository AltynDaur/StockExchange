package kz.altynbekov.stockexchange.parsing;

import kz.altynbekov.stockexchange.model.ClientBalance;

public class ClientBalanceDeserializer extends AbstractDeserializer {

    public ClientBalance deserialize(String line) {
        String[] stringParts = getStringParts(line);
        return new ClientBalance(stringParts[0],
                Integer.parseInt(stringParts[1]),
                Integer.parseInt(stringParts[2]),
                Integer.parseInt(stringParts[3]),
                Integer.parseInt(stringParts[4]),
                Integer.parseInt(stringParts[5]));
    }
}
