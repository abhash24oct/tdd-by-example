package guru.springframework;

import java.util.HashMap;

public class Bank {

    private final HashMap<Pair, Integer> rateMap = new HashMap<>();

    Money reduce(Expression source, Currency toCurrency){
        return  source.reduce(this,toCurrency);
    }

    public  int rate(Currency from, Currency to){
        if(from.equals(to))
            return 1;
        return rateMap.get(new Pair(from,to));
    }

    public void addRate(Currency from, Currency to,int rate){
        rateMap.put(new Pair(from,to),rate);
    }
}
