package com.biradar.sidagond.accounts.serviceImpl;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biradar.sidagond.accounts.constants.AccountsConstants;
import com.biradar.sidagond.accounts.dto.AccountsDto;
import com.biradar.sidagond.accounts.dto.CustomerDto;
import com.biradar.sidagond.accounts.entity.Accounts;
import com.biradar.sidagond.accounts.entity.Customer;
import com.biradar.sidagond.accounts.entityMappers.AccountsMapper;
import com.biradar.sidagond.accounts.entityMappers.CustomerMapper;
import com.biradar.sidagond.accounts.exceptions.CustomerAlreadyRegisteredException;
import com.biradar.sidagond.accounts.exceptions.ResourceNotFoundException;
import com.biradar.sidagond.accounts.repository.AccountRepository;
import com.biradar.sidagond.accounts.repository.CustomerRepository;
import com.biradar.sidagond.accounts.service.AccountsService;

@Service
public class AccountsServiceImpl implements AccountsService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private AccountsServiceImpl(AccountRepository accountRepo, CustomerRepository customerRepo) {
		this.accountRepository = accountRepo;
		this.customerRepository = customerRepo;
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		Optional<Customer> dataBaseCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if (dataBaseCustomer.isPresent()) {
			throw new CustomerAlreadyRegisteredException("Customer is already registered with this mobile Number");
		}
		Customer customer = CustomerMapper.mapCustomerDtoToCustomer(customerDto, new Customer());
//		customer.setCreatedAt(LocalDateTime.now());
//		customer.setCreatedBy("Sidagond");
		Customer savedCustomer = customerRepository.save(customer);
		accountRepository.save(createNewAccount(savedCustomer));
	}

	private Accounts createNewAccount(Customer customer) {
		Accounts accounts = new Accounts();
		accounts.setCustomerId(customer.getCustomerId());
		long accountNumber = 1000000000L + new Random().nextInt(900000000);
		accounts.setAccountNumber(accountNumber);
		accounts.setAccountType(AccountsConstants.SAVINGS);
		accounts.setBranchAddress(AccountsConstants.ADDRESS);
//		accounts.setCreatedAt(LocalDateTime.now());
//		accounts.setCreatedBy("Sidagond");
		return accounts;
	}

	@Override
	public CustomerDto getAccountDetails(String mobileNum) {
		Customer dataBaseCustomer = customerRepository.findByMobileNumber(mobileNum)
				.orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNum", mobileNum));
		Accounts databaseAccount = accountRepository.findByCustomerId(dataBaseCustomer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId",
						dataBaseCustomer.getCustomerId().toString()));

		CustomerDto mappedCustomerDto = CustomerMapper.mapCustomerToDto(dataBaseCustomer, new CustomerDto());
		mappedCustomerDto.setAccountsDto(AccountsMapper.mapAccountsToDto(databaseAccount, new AccountsDto()));
		return mappedCustomerDto;
	}

	@Override
	public boolean updateAccountDetails(CustomerDto customerDto) {
		boolean isUpdated = false;
		AccountsDto accountsDto = customerDto.getAccountsDto();
		if (accountsDto != null) {
			Accounts dbAccount = accountRepository.findById(accountsDto.getAccountNumber())
					.orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber",
							accountsDto.getAccountNumber().toString()));
			AccountsMapper.mapAccountsDtoToAccounts(accountsDto, dbAccount);
			dbAccount = accountRepository.save(dbAccount);

			Long customerId = dbAccount.getCustomerId();
			Customer dbCustomer = customerRepository.findById(customerId)
					.orElseThrow(() -> new ResourceNotFoundException("Customer", "CustomerId", customerId.toString()));
			CustomerMapper.mapCustomerDtoToCustomer(customerDto, dbCustomer);
			customerRepository.save(dbCustomer);
			isUpdated = true;

		}
		return isUpdated;
	}

	@Override
	public boolean deleteAccountDetails(String mobileNumber) {
		Customer dbCustomer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer"," mobileNumber", mobileNumber));
		accountRepository.deleteByCustomerId(dbCustomer.getCustomerId());
		customerRepository.deleteById(dbCustomer.getCustomerId());
		return true;
	}

}
