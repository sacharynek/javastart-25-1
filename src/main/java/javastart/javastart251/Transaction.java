package javastart.javastart251;



import java.math.BigDecimal;
import java.sql.Date;

public class Transaction {

    private long id;
    private boolean ttype;
    private String description;
    private BigDecimal amount; //js4 354 https://www.baeldung.com/java-money-and-currency
    private Date date;

    public void setId(long id) {
        this.id = id;
    }

    public void setTtype(boolean ttype) {
        this.ttype = ttype;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public boolean isTtype() {
        return ttype;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public boolean getTType(){
        return this.ttype;
    }

    public Transaction() {
    }

    public Transaction(long id, boolean ttype, String description, BigDecimal amount, Date date) {
        this.id = id;
        this.ttype = ttype;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", ttype=" + ttype +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}

