package net.javaguides.banking.controller;

import net.javaguides.banking.dto.AccountDTO;
import net.javaguides.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
      // add account rest api
    @PostMapping
    public ResponseEntity<AccountDTO> addAccount(@RequestBody AccountDTO accountDTO){
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);

    }

    //Get account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long id){
        AccountDTO accountDTO= accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDTO);
    }
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> depositAmount(@PathVariable Long id , @RequestBody Map<String, Double> request) {
        Double amount =request.get("amount");
     AccountDTO accountDTO=accountService.deposit(id,amount);
     return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withDraw(@PathVariable Long id , @RequestBody Map<String, Double> request) {
        Double amount =request.get("amount");
        AccountDTO accountDTO=accountService.withDraw(id,amount);
        return ResponseEntity.ok(accountDTO);
    }

    //Get All Account REST API
    @GetMapping
    public ResponseEntity<List<AccountDTO>>getAllAccount(){
        List<AccountDTO> accountDTO = accountService.getAllAccount();
        return ResponseEntity.ok(accountDTO);
    }

    //Delete Account REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is deleted succesfully !");
    }
}
