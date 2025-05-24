package net.javaguides.banking.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class AccountDTO {
    public Long id;
    private String accountHolderName;
    private double balance;
}
