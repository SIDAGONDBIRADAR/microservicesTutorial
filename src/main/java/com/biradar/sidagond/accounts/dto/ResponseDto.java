package com.biradar.sidagond.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
		name="Response",
		description = "Schema to hold Response information"
		)
public class ResponseDto {

	@Schema(
			description = "Response Code",example = "200"
			)
	private String responseCode;
	
	@Schema(
			description = "Respons Message ",example = "Request Processed Successfully"
			)
	private String responseMessage;

	public ResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseDto(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

}
