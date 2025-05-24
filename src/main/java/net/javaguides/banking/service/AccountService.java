package net.javaguides.banking.service;

import net.javaguides.banking.dto.AccountDTO;

import java.util.List;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountById(Long id);
    AccountDTO deposit(Long id, double amount);

    AccountDTO withDraw(Long id, double amount);

    List<AccountDTO> getAllAccount();

    void deleteAccount(Long id);
}
