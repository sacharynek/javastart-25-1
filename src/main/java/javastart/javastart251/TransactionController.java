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

    private TransactionRepository transactionRepository;

    private TransactionRepository getTransactionRepository(){
        return this.transactionRepository;
    }

    public TransactionController(TransactionRepository transactionRepository){
        this.transactionRepository  = transactionRepository;

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
    public String addProduct(Model model, @RequestParam() boolean ttype,
            @RequestParam() String description,
            @RequestParam Monetary amount,
            @RequestParam Date date) {


       getTransactionRepository().addTransaction(new Transaction());


        return "redirect:/DisplayWithPagination.html";
    }

}
