package com.example.demo.income;

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

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class IncomeJpaResource {

	@Autowired
    private IncomeJpaRepo incomeJpaRepo;

    @GetMapping("/jpa/users/{username}/incomes")
    public List<Income> getAllIncomes(@PathVariable String username){
       
        return incomeJpaRepo.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/incomes/{id}")
    public Income getIncome(@PathVariable String username,
                        @PathVariable Long id){
        
        return incomeJpaRepo.findById(id).get();

    }

    @DeleteMapping("/jpa/users/{username}/incomes/{id}")
    public ResponseEntity<Void> deleteIncome(@PathVariable String username,
                                           @PathVariable Long id){

       
    	incomeJpaRepo.deleteById(id);


        return ResponseEntity.noContent().build();


    }

    @PutMapping("/jpa/users/{username}/incomes/{id}")
    public ResponseEntity<Income> updateIncome(@PathVariable String username,
                                           @PathVariable Long id,
                                           @RequestBody Income income){

      
    	Income incomeUpdated = incomeJpaRepo.save(income);

        return new ResponseEntity<Income>(income, HttpStatus.OK);

    }

    @PostMapping("/jpa/users/{username}/incomes")
    public ResponseEntity<Void> createIncome(@PathVariable String username,
                                           @RequestBody Income income){

    	income.setUsername(username);
        Income createdIncome = incomeJpaRepo.save(income);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdIncome.getId()).toUri();


        return ResponseEntity.created(uri).build();

    }


}
