package guru.springframework;

public interface Expression {

    Expression plus(Expression addend);

    Money reduce(Bank bank, Currency to);

    Expression times(int multiplier);

}
