package com.java.banking.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.java.banking.dto.AccountDto;
import com.java.banking.entity.Account;
import com.java.banking.mapper.AccountMapper;
import com.java.banking.repository.AccountRepository;
import com.java.banking.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService{
	
	private AccountRepository accountRepository;
	

	public AccountServiceImpl(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountdto(savedAccount);
		
	}


	@Override
	public AccountDto getAccountById(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exist"));
		return AccountMapper.maptoAccountdto(account);
	}


	@Override
	public AccountDto deposit(Long id, double amount) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exist"));
		double total = account.getBalance()+amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountdto(savedAccount);
	}


	@Override
	public AccountDto withdraw(Long id, double amount) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exist"));
		
		if(account.getBalance() < amount) {
			throw new RuntimeException("Insufficient Balance");
		}
		double total = account.getBalance()-amount;
		account.setBalance(total);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountdto(savedAccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)->AccountMapper.maptoAccountdto(account))
		.collect(Collectors.toList());
		 
	}


	@Override
	public void deleteAccount(Long id) {
		Account account=accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("account does not exist"));
		
		accountRepository.deleteById(id);
	}

}
