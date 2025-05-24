package net.javaguides.banking.mapper;

import net.javaguides.banking.dto.AccountDTO;
import net.javaguides.banking.entity.Account;

public class AccountMapper {
    public static Account maptoAccount(AccountDTO accountDTO){
    Account account1;
        account1 = new Account(
                accountDTO.getId(),
                accountDTO.getAccountHolderName(),
                accountDTO.getBalance()
        );
        return account1;
    }

    public static AccountDTO mapToAccountDto(Account account){
        AccountDTO accountDTO= new AccountDTO(account.getId() ,
                account.getAccountHolderName(),
                account.getBalance()
                );
        return accountDTO;
    }
}
