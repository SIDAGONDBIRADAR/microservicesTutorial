package com.biradar.sidagond.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name="Accounts",
		description = "Schema to hold  Account Details"
		)
public class AccountsDto {

	@Schema(
			description = "Account Number of a customer in MyBank",example = "1987456342"
			)
	@Pattern(regexp = "(^$|[0-9]{10})",message = "Account Number should be of 10 digits")
	private Long accountNumber;
	
	@Schema(
			description = "Account Type of Customer",example = "Savings"
			)
	@NotEmpty(message = "Account Type cannot be empty")
	private String accountType;
	
	@Schema(
			description = "Address of MyBank Branch",example = "69th cross 33rd Main road Tunisandra"
			)
	@NotEmpty(message = "branch address cannot be empty")
	private String branchAddress;

	public AccountsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountsDto(Long accountNumber, String accountType, String branchAddress) {
		super();
		this.accountNumber = accountNumber;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

}
