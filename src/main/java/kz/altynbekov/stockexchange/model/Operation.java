package kz.altynbekov.stockexchange.model;

public class Operation {

    private final String clientName;
    private final String operationType;
    private final String noteType;
    private final int noteCount;
    private final int notePrice;


    public Operation(String clientName, String operationType, String noteType, int noteCount, int notePrice) {
        this.clientName = clientName;
        this.operationType = operationType;
        this.noteType = noteType;
        this.noteCount = noteCount;
        this.notePrice = notePrice;
    }

    public String getClientName() {
        return clientName;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getNoteType() {
        return noteType;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public int getNotePrice() {
        return notePrice;
    }
}
