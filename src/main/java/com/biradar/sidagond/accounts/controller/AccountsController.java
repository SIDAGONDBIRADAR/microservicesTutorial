package com.biradar.sidagond.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.biradar.sidagond.accounts.constants.AccountsConstants;
import com.biradar.sidagond.accounts.dto.CustomerDto;
import com.biradar.sidagond.accounts.dto.ErrorResponseDto;
import com.biradar.sidagond.accounts.dto.ResponseDto;
import com.biradar.sidagond.accounts.service.AccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Tag(name = "Accounts MicroServices CRUD Operations Documentation", description = "Create,Read,Update,Delete operations for Accounts MicroServices")
public class AccountsController {

	@Autowired
	private AccountsService accountsService;

	@PostMapping("/createAccount")
	@Operation(summary = "Create Account Rest API", description = "Create new Customer and Account in MyBank APP")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
		@ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

})
	public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
		accountsService.createAccount(customerDto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
	}

	@Operation(summary = "Read or Fetch Customer and Accounts Details REST API", description = "Read or Fetch customer and his accounts details by mobileNumber")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
		@ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

})
	@GetMapping("/getAccountDetails")
	public ResponseEntity<CustomerDto> getAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be of 10 digits") String mobileNumber) {
		CustomerDto dbDetails = accountsService.getAccountDetails(mobileNumber);
		return ResponseEntity.status(HttpStatus.OK).body(dbDetails);
	}

	@Operation(summary = "Update Customer and Accounts Details REST API", description = "Update Customer and his accounts details")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "HTTP Status UPDATED"),
			@ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR", content = @Content(schema = @Schema(implementation = ErrorResponseDto.class)))

	})
	@PutMapping("/updateAccountDetails")
	public ResponseEntity<ResponseDto> updateAccuntDetails(@Valid @RequestBody CustomerDto customerDto) {
		boolean updatedAccount = accountsService.updateAccountDetails(customerDto);
		if (updatedAccount) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.MESSAGE_500, AccountsConstants.MESSAGE_500));
		}
	}

	@Operation(summary = "Delete Customer and Accounts Details REST API", description = "Delete Customer and his accounts details by providing mobileNumber")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "HTTP Status OK"),
			@ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR")

	})
	@DeleteMapping("/deleteAccountDetails")
	public ResponseEntity<ResponseDto> deleteAccountDetails(
			@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number should be of 10 digits") String mobileNumber) {
		boolean isDeleted = accountsService.deleteAccountDetails(mobileNumber);
		if (isDeleted) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseDto(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
		}
	}
}
