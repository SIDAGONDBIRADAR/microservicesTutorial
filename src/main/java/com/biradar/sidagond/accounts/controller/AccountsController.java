package com.biradar.sidagond.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biradar.sidagond.accounts.constants.AccountsConstants;
import com.biradar.sidagond.accounts.dto.CustomerDto;
import com.biradar.sidagond.accounts.dto.ResponseDto;
import com.biradar.sidagond.accounts.service.AccountsService;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountsController {

	@Autowired
	private AccountsService accountsService;

	@PostMapping("/createAccount")
	public ResponseEntity<ResponseDto> createAccount(@RequestBody CustomerDto customerDto) {
		accountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@GetMapping("/getAccountDetails")
	public ResponseEntity<CustomerDto> getAccountDetails(@RequestParam String mobileNumber) {
		CustomerDto dbDetails = accountsService.getAccountDetails(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(dbDetails);
	}
}
