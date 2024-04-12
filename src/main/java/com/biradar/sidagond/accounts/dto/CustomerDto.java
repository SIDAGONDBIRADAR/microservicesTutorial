package com.biradar.sidagond.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Schema for Customer and Account Details", name = "Customer")
public class CustomerDto {

	@Schema(
			description = "Name of a Customer",example = "Monkey D Luffy"
			)
	@NotEmpty(message = "Name cannot be empty")
	@Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
	private String name;

	@Schema(
			description = "Email of a customer",example = "pirateKing@Sunny.com"
			)
	@NotEmpty(message = "Email should be empty")
	@Email(message = "Please enter valid email address")
	private String email;

	@Schema(
			description = "Mobile Number of a customer",example = "1987456342"
			)
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be of 10 digits")
	private String mobileNumber;
	
	@Schema(
			description = "Account Details of a customer"
			)
	private AccountsDto accountsDto;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public AccountsDto getAccountsDto() {
		return accountsDto;
	}

	public void setAccountsDto(AccountsDto accountsDto) {
		this.accountsDto = accountsDto;
	}

}
