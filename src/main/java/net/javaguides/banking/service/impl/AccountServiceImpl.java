package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDTO;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.mapper.AccountMapper;
import net.javaguides.banking.repository.AccountRepository;
import net.javaguides.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account= AccountMapper.maptoAccount(accountDTO);
        Account Saveaccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(Saveaccount);
    }
    @Override
    public  AccountDTO getAccountById(Long id){
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDTO deposit(Long id, double amount) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        double total =  account.getBalance() + amount;
        account.setBalance(total);
        Account Saveaccount =accountRepository.save(account);
        return AccountMapper.mapToAccountDto(Saveaccount);
    }

    @Override
    public AccountDTO withDraw(Long id, double amount) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));

        if(account.getBalance()<amount) {
            throw new RuntimeException("Insufficient Balance");
        }
            double total = account.getBalance() - amount;
            account.setBalance(total);
            Account Saveaccount = accountRepository.save(account);
            return AccountMapper.mapToAccountDto(Saveaccount);

    }

    @Override
    public List<AccountDTO> getAllAccount() {
        List<Account> account = accountRepository.findAll();
        return account.stream().map((accounts) -> AccountMapper.mapToAccountDto(accounts))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        Account account=accountRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("Account does not exist"));
        accountRepository.deleteById(id);
    }
}
