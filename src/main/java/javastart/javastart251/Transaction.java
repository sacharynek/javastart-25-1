package javastart.javastart251;

import javax.money.Monetary;
import java.sql.Date;

public class Transaction {

    private int id;
    private boolean ttype;
    private String description;
    private Monetary amount; //js4 354 https://www.baeldung.com/java-money-and-currency
    private Date date;

}

