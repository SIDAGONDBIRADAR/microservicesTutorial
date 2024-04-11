package com.biradar.sidagond.accounts.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.biradar.sidagond.accounts.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerAlreadyRegisteredException.class)
	public ResponseEntity<ErrorResponseDto> hanldeCustomerAlreadyExistsException(
			CustomerAlreadyRegisteredException exception,WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.BAD_REQUEST,
				exception.getMessage(),
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
			ResourceNotFoundException exception,WebRequest webRequest){
		ErrorResponseDto errorResponseDto = new ErrorResponseDto(
				webRequest.getDescription(false),
				HttpStatus.NOT_FOUND,
				exception.getMessage(),
				LocalDateTime.now()
				);
		
		return new ResponseEntity<>(errorResponseDto,HttpStatus.BAD_REQUEST);
	}
}