package javastart.javastart251;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;

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

        return "DisplayWithPagination";
    }

    @GetMapping("/add")
    public String add(Model model) {

        return "Add";
    }

    @RequestMapping("/addProduct")
    public String addProduct(Model model, @RequestParam() String ttype,
            @RequestParam() String description,
            @RequestParam String amount,
            @RequestParam Date date) {


       getTransactionDao().insert(new Transaction());


        return "redirect:/DisplayAll";
    }

    @GetMapping("/DisplayAll")
    public String displayAll(Model model) {





        return "Index";
    }



}
