package kz.altynbekov.stockexchange.parsing;

import kz.altynbekov.stockexchange.model.Operation;

public class OperationDeserializer extends AbstractDeserializer {

    public Operation deserialize(String stringLine) {
        String[] stringParts = getStringParts(stringLine);
        return new Operation(stringParts[0],
                stringParts[1],
                stringParts[2],
                Integer.parseInt(stringParts[3]),
                Integer.parseInt(stringParts[4]));
    }
}
