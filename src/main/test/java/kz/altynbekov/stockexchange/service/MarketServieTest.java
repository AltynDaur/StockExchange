package kz.altynbekov.stockexchange.service;

import kz.altynbekov.stockexchange.model.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

public class MarketServieTest {

    private MarketService underTests = new MarketService();

    @Before
    public void init() {
        underTests.addOperation(new Operation("test1", "s", "A", 100, 14));
        underTests.addOperation(new Operation("test2", "b", "B", 10, 15));
    }

    @Test
    public void givenExistingOperationWhenGetOppositeOperationThenOptionalIsNotEmpty() {
        Operation currentOperation = new Operation("test3", "s", "B", 10, 15);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(currentOperation);
        Assert.assertTrue(operationOptional.isPresent());
    }

    @Test
    public void givenSameClientOperationWhenGetOppositeOperationThenOptionalIsEmpty() {
        Operation currentOperation = new Operation("test2", "s", "B", 10, 15);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(currentOperation);
        Assert.assertFalse(operationOptional.isPresent());
    }

    @Test
    public void givenSameOperationTypeOperationWhenGetOppositeOperationThenOptionalIsEmpty() {
        Operation currentOperation = new Operation("test3", "b", "B", 10, 15);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(currentOperation);
        Assert.assertFalse(operationOptional.isPresent());
    }

    @Test
    public void givenNotSameNoteCountOperationWhenGetOppositeOperationThenOptionalIsEmpty() {
        Operation currentOperation = new Operation("test3", "s", "B", 140, 15);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(currentOperation);
        Assert.assertFalse(operationOptional.isPresent());
    }

    @Test
    public void givenNotSamePriceOperationWhenGetOppositeOperationThenOptionalIsEmpty() {
        Operation currentOperation = new Operation("test3", "s", "B", 10, 158);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(currentOperation);
        Assert.assertFalse(operationOptional.isPresent());
    }

    @Test
    public void givenOperationWhenAddOperationThenOptionalIsNotEmpty() {
        Operation currentOperation = new Operation("test3", "s", "C", 10, 15);
        underTests.addOperation(currentOperation);
        Operation oppositeOperation = new Operation("test4", "b", "C", 10, 15);
        Optional<Operation> operationOptional = underTests.getOppositeOperation(oppositeOperation);
        Assert.assertTrue(operationOptional.isPresent());
        underTests.finishOperation(currentOperation);
        Optional<Operation> operationOptionalAfterFinishing = underTests.getOppositeOperation(oppositeOperation);
        Assert.assertFalse(operationOptionalAfterFinishing.isPresent());
    }
}
