package guru.springframework;

public class Sum  implements Expression{

    Expression augmend;
    Expression addemend;

    public Sum(Expression augmend, Expression addemend) {
        this.augmend = augmend;
        this.addemend = addemend;
    }

    @Override
    public Expression plus(Expression addend) {
        return null;
    }

    @Override
    public Money reduce(Bank bank, Currency to) {
    return  new Money(augmend.reduce(bank,to).amount+ addemend.reduce(bank,to).amount,to);
    }
}
