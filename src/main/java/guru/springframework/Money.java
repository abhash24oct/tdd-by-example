package guru.springframework;

import org.jetbrains.annotations.NotNull;

public   class Money implements Expression {
    protected final int amount;
    protected final Currency currency;

    @Override
    public String toString() {
        return "Money{" +
                "amount=" + amount +
                ", currency=" + currency +
                '}';
    }

    public Money(int amount, Currency currency) {
        this.amount=amount;
        this.currency=currency;
    }

     public static Money dollar(int amount) {
        return new Money(amount,Currency.USD);
    }

    public static  Money franc(int amount){
        return new Money(amount,Currency.CHF);
    }

    public boolean equals(Object obj){
        return ((Money) obj).amount == this.amount && this.currency().toString().equals(((Money) obj).currency.toString());
    }



    protected Currency currency() {
        return currency;
    }

    @Override
    public Expression times(int multiplier){
        return  new Money(amount * multiplier,this.currency) ;
    }

    @Override
    public Expression plus(Expression addend){
        return  new Sum(this,addend);
    }

    @Override
    public Money reduce(@NotNull Bank bank, Currency to) {
        return new Money(this.amount/bank.rate(this.currency,to),to);
    }
}
