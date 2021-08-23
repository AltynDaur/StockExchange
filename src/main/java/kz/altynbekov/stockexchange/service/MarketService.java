package kz.altynbekov.stockexchange.service;

import kz.altynbekov.stockexchange.model.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MarketService {

    private final List<Operation> unprocessedOperations = new ArrayList<>();

    public Optional<Operation> getOppositeOperation(Operation currentOperation) {
        return unprocessedOperations.stream()
                .filter(operation -> isOppositeOperations(operation, currentOperation))
                .findFirst();

    }

    public void finishOperation(Operation operation) {
        unprocessedOperations.remove(operation);
    }

    public void addOperation(Operation currentOperation) {
        unprocessedOperations.add(currentOperation);
    }

    private boolean isOppositeOperations(Operation operation, Operation currentOperation) {
        return !operation.getClientName().equals(currentOperation.getClientName())
                && !operation.getOperationType().equals(currentOperation.getOperationType())
                && operation.getNoteType().equals(currentOperation.getNoteType())
                && operation.getNoteCount() == currentOperation.getNoteCount()
                && operation.getNotePrice() == currentOperation.getNotePrice();
    }
}
