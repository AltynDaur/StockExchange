package kz.altynbekov.stockexchange.service;

import kz.altynbekov.stockexchange.model.ClientBalance;
import kz.altynbekov.stockexchange.model.Operation;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class StockServiceTest {

    private StockService underTests = new StockService();

    @Test
    public void givenBuyOperationAndClientBalanceWhenProcessOperationThenClientBalanceHasChanged() {
        Operation operation = new Operation("test1", "b", "A", 100, 14);
        ClientBalance clientBalance = new ClientBalance("test1", 10000, 10, 10, 10, 10);
        underTests.processOperation(operation, clientBalance);

        Assert.assertEquals(clientBalance.getBalance(), 8600);
        Assert.assertEquals(clientBalance.getNoteACount(), 110);
    }

    @Test
    public void givenSellOperationAndClientBalanceWhenProcessOperationThenClientBalanceHasChanged() {
        Operation operation = new Operation("test1", "s", "A", 100, 14);
        ClientBalance clientBalance = new ClientBalance("test1", 10000, 150, 10, 10, 10);
        underTests.processOperation(operation, clientBalance);

        Assert.assertEquals(clientBalance.getBalance(), 11400);
        Assert.assertEquals(clientBalance.getNoteACount(), 50);
    }
}
