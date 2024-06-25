package com.java.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AccountDto {
	
	
	private Long id;
	private String accountHolderName;
	private Double balance;

}
