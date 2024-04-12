package com.biradar.sidagond.accounts.service;

import com.biradar.sidagond.accounts.dto.CustomerDto;

public interface AccountsService {
	
	public void createAccount(CustomerDto customerDto);
	public CustomerDto getAccountDetails(String mobileNum);
	public boolean updateAccountDetails(CustomerDto customerDto);
	public boolean deleteAccountDetails(String mobileNumber);
}
