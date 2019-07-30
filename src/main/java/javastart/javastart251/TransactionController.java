package javastart.javastart251;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.money.Monetary;

@Controller
public class TransactionController {

    private TransactionDao transactionDao;

    private TransactionDao getTransactionDao(){
        return this.transactionDao;
    }

    public TransactionController(){
        this.transactionDao = new TransactionDao();

    }


    //potem dodać DTO i bazę danych
    @GetMapping("/all")
    public String displayWithPaginationController(Model model) {

        List<Transaction> transactions = transactionDao.getAllTransactions();
        model.addAttribute("transactions", transactions);


        return "DisplayWithPagination";
    }

    @GetMapping("/add")
    public String add(Model model) {

        return "Add";
    }

    @GetMapping("/Delete/{id}")
    public String delete(Model model, @PathVariable(value="id") String id) {
        transactionDao.deleteByID(Long.parseLong(id));
        return "redirect:/all";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model, @RequestParam() String ttype,
            @RequestParam() String description,
            @RequestParam String amount,
            @RequestParam Date date) {
            Transaction transaction = new Transaction();
            transaction.setDescription(description);
            transaction.setDate(date);
            transaction.setAmount(new BigDecimal(amount));
            transaction.setTtype(ttype.equals("true"));

       getTransactionDao().insert(transaction);


        return "redirect:/all";
    }



    @GetMapping("/DisplayDetails/{transactionID}")
    public String displayDetails(Model model, @PathVariable(value="transactionID") String id){

        Transaction transaction = transactionDao.findByID(Long.parseLong(id));


        model.addAttribute("transaction", transaction);
        return "DisplayDetails";
    }

}
