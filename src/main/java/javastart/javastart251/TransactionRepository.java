package javastart.javastart251;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TransactionRepository {

    private List<Transaction> transactions= new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
