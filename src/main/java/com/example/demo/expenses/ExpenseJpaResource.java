package com.example.demo.expenses;

import java.io.FileNotFoundException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ExpenseJpaResource {
	@Autowired
    private ExpenseJpaRepo expenseJpaRepo;
	
	@Autowired
	private ExpenseReportService expenseReportService;

    @GetMapping("/jpa/users/{username}/expenses")
    public List<Expense> getAllExpenses(@PathVariable String username){
      
        return expenseJpaRepo.findByUsername(username);
    }

    @GetMapping("/jpa/users/category/{category}/expenses")
    public List<Expense> searchCategory(@PathVariable String category){
      
        return expenseJpaRepo.findBycategoryContainingIgnoreCase(category);
    }
    
    @GetMapping("/jpa/users/{username}/expenses/{id}")
    public Expense getExpense(@PathVariable String username,
                        @PathVariable Long id){
       
        return expenseJpaRepo.findById(id).get();

    }

    @DeleteMapping("/jpa/users/{username}/expenses/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String username,
                                           @PathVariable Long id){

    	expenseJpaRepo.deleteById(id);


        return ResponseEntity.noContent().build();


    }

    @PutMapping("/jpa/users/{username}/expenses/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String username,
                                           @PathVariable Long id,
                                           @RequestBody Expense expense){

    	Expense expenseUpdated = expenseJpaRepo.save(expense);

        return new ResponseEntity<Expense>(expense, HttpStatus.OK);

    }

    @PostMapping("/jpa/users/{username}/expenses")
    public ResponseEntity<Void> createExpense(@PathVariable String username,
                                           @RequestBody Expense expense){

    	expense.setUsername(username);
    	Expense createdExpense = expenseJpaRepo.save(expense);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdExpense.getId()).toUri();


        return ResponseEntity.created(uri).build();

    }
    
    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
    	return expenseReportService.exportReport(format);
    	
    }


}
