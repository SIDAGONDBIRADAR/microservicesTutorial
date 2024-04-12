package com.biradar.sidagond.accounts.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "Schema for ErrorResponse Details", name = "Errorresponse")
public class ErrorResponseDto {

	private String invokedApi;
	private HttpStatus errorCode;
	private String errorMessage;
	private LocalDateTime errorTime;

	public ErrorResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ErrorResponseDto(String invokedApi, HttpStatus errorCode, String errorMessage, LocalDateTime errorTime) {
		super();
		this.invokedApi = invokedApi;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

	public String getInvokedApi() {
		return invokedApi;
	}

	public void setInvokedApi(String invokedApi) {
		this.invokedApi = invokedApi;
	}

	
	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

}
