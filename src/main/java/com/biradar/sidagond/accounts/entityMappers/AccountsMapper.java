package com.biradar.sidagond.accounts.entityMappers;

import com.biradar.sidagond.accounts.dto.AccountsDto;
import com.biradar.sidagond.accounts.entity.Accounts;

public class AccountsMapper {

	public static AccountsDto mapAccountsToDto(Accounts accounts, AccountsDto accountsDto) {
		accountsDto.setAccountNumber(accounts.getAccountNumber());
		accountsDto.setAccountType(accounts.getAccountType());
		accountsDto.setBranchAddress(accounts.getBranchAddress());
		return accountsDto;
	}

	public static Accounts mapAccountsDtoToAccounts(AccountsDto accountsDto, Accounts accounts) {
		accounts.setAccountNumber(accountsDto.getAccountNumber());
		accounts.setAccountType(accountsDto.getAccountType());
		accounts.setBranchAddress(accountsDto.getBranchAddress());
		return accounts;
	}

}
