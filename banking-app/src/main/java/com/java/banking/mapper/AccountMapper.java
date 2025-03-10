package com.java.banking.mapper;

import com.java.banking.dto.AccountDto;
import com.java.banking.entity.Account;

public class AccountMapper {
	public static Account mapToAccount(AccountDto accountDto) {
		Account account = new Account(
				accountDto.getId(),
				accountDto.getAccountHolderName(),
				accountDto.getBalance()
				
				);
		return account;
	}
	
	public static AccountDto maptoAccountdto(Account account) {
		AccountDto accountDto = new AccountDto(
				account.getId(),
				account.getAccountHolderName(),
				account.getBalance()
				
				);
		return accountDto;
	}

}
