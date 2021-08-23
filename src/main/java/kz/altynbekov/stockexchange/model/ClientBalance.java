package kz.altynbekov.stockexchange.model;

public class ClientBalance {

    private final String clientName;
    private int balance;
    private int noteACount;
    private int noteBCount;
    private int noteCCount;
    private int noteDCount;

    public ClientBalance(String clientName, int balance, int noteACount, int noteBCount, int noteCCount, int noteDCount) {
        this.clientName = clientName;
        this.balance = balance;
        this.noteACount = noteACount;
        this.noteBCount = noteBCount;
        this.noteCCount = noteCCount;
        this.noteDCount = noteDCount;
    }


    public int getNoteACount() {
        return noteACount;
    }

    public void setNoteACount(int noteACount) {
        this.noteACount = noteACount;
    }

    public int getNoteBCount() {
        return noteBCount;
    }

    public void setNoteBCount(int noteBCount) {
        this.noteBCount = noteBCount;
    }

    public int getNoteCCount() {
        return noteCCount;
    }

    public void setNoteCCount(int noteCCount) {
        this.noteCCount = noteCCount;
    }

    public int getNoteDCount() {
        return noteDCount;
    }

    public void setNoteDCount(int noteDCount) {
        this.noteDCount = noteDCount;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getClientName() {
        return clientName;
    }
}
