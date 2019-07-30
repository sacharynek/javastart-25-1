package javastart.javastart251;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Javastart251ApplicationTests {

    private  TransactionDao transactionDao;


    @Before
    public  void setUp(){
        this.transactionDao = new TransactionDao();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void TransactionDaoInsertTest(){
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(99.99));
        transaction.setTtype(true);
        transaction.setDate(new Date(2656987L));
        transaction.setDescription("blablabla");

        transactionDao.insert(transaction);
    }

    @Test
    public void TransactionDaoReadTest(){
        Transaction transaction = transactionDao.findByID(1L);
        System.out.println(transaction);
    }

    @Test
    public void TransactionDaoUpdateTest(){
        Transaction transaction = new Transaction();
        transaction.setAmount(new BigDecimal(199.99));
        transaction.setTtype(true);
        transaction.setDate(new Date(2656987L));
        transaction.setDescription("edytowane bla bla bla");
        transaction.setId(1L);

        transactionDao.update(transaction);
    }

    @Test
    public void TransactionDaoDeleteTest(){
        transactionDao.deleteByID(10L);
    }

    @Test
    public void TransactionDaoGetAllTransactionsTest(){
        List<Transaction> transakcje = transactionDao.getAllTransactions();
        System.out.println(transakcje);
    }

}
