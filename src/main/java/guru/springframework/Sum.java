package guru.springframework;

public class Sum  implements Expression{

    final Expression augmend;
    final Expression addemend;

    public Sum(Expression augmend, Expression addemend) {
        this.augmend = augmend;
        this.addemend = addemend;
    }

    @Override
    public Expression plus(Expression addend) {
        return new Sum(this,addemend);
    }

    @Override
    public Money reduce(Bank bank, Currency to) {
    return  new Money(augmend.reduce(bank,to).amount+ addemend.reduce(bank,to).amount,to);
    }

    @Override
    public Expression times(int multiplier) {
        return new Sum(this.augmend.times(multiplier),this.addemend.times(multiplier));
    }
}
