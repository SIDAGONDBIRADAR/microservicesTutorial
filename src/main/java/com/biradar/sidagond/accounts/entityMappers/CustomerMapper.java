package com.biradar.sidagond.accounts.entityMappers;

import com.biradar.sidagond.accounts.dto.CustomerDto;
import com.biradar.sidagond.accounts.entity.Customer;

public class CustomerMapper {

	public static CustomerDto mapCustomerToDto(Customer customer, CustomerDto customerDto) {
		customerDto.setName(customer.getName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setMobileNumber(customer.getMobileNumber());
		return customerDto;
	}

	public static Customer mapCustomerDtoToCustomer(CustomerDto customerDto, Customer customer) {
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setMobileNumber(customerDto.getMobileNumber());
		return customer;
	}

}
