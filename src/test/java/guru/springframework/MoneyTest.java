package guru.springframework;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class MoneyTest {

    @Test
    void testMultiplicationDollar(){

        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10),five.times(2));
        assertEquals(Money.dollar(15),five.times(3));
        Money fiveF = Money.franc(5);
        assertEquals(Money.franc(10),fiveF.times(2));

    }

    @Test
    void testEqualityDollar(){
        assertEquals(Money.dollar(5),Money.dollar(5));
        assertNotEquals(Money.dollar(5), Money.dollar(6));
        assertEquals(Money.franc(5),Money.franc(5));
        assertNotEquals(Money.franc(5),Money.franc(6));
        assertNotEquals(Money.franc(5),Money.dollar(5));
    }

    @Test
    void testCurrency(){
        assertEquals(Currency.USD,Money.dollar(1).currency());
        assertEquals(Currency.CHF,Money.franc(1).currency());
    }

    @Test
    void testSimpleAddition(){
        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum,Currency.USD);
        assertEquals(Money.dollar(10),reduced);
    }

    @Test
    void testPlusReturnsSum(){
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertEquals(five,sum.augmend);
        assertEquals(five,sum.addemend);
    }

    @Test
    void testReduceSum(){
        Expression sum = new Sum(Money.dollar(3),Money.dollar(4));
        Bank bank = new Bank();
        Money result = bank.reduce(sum,Currency.USD);
        assertEquals(Money.dollar(7),result);
    }

    @Test
    void testReduceMoney(){
        Bank bank = new Bank();
        Money result = bank.reduce(Money.dollar(1),Currency.USD);
        assertEquals(Money.dollar(1),result);

    }

    @Test
    void testReduceMoneyDifferentCurrency() {
        Bank bank = new Bank();
        bank.addRate(Currency.CHF, Currency.USD, 2);
        Money result = bank.reduce(Money.franc(2), Currency.USD);
        assertEquals(Money.dollar(1), result);
    }

    @Test
    void testIdentityRate() {
        assertEquals(1, new Bank().rate(Currency.USD, Currency.USD));
        assertEquals(1, new Bank().rate(Currency.CHF, Currency.CHF));
    }

    @Test
    public void testMixedAddition() {
        Expression fiveBucks = Money.dollar(5);
        Expression tenFrancs = Money.franc(10);
        Bank bank = new Bank();
        bank.addRate(Currency.CHF, Currency.USD, 2);
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), Currency.USD);
        assertEquals(Money.dollar(10), result);
    }
}
