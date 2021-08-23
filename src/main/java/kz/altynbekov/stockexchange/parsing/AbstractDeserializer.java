package kz.altynbekov.stockexchange.parsing;

public abstract class AbstractDeserializer {

    public static final String DELIMITER = "\\t";

    protected String[] getStringParts(String line) {
        return line.split(DELIMITER);
    }

}
