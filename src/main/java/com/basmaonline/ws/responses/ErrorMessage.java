package com.basmaonline.ws.responses;

public enum ErrorMessage {
	MISSING_REQUIRED_FIELD("Missing Required Field."),
	RECORD_ALREADY_EXISTS("Record Already exists."),
	INTERNAL_SERVER_ERROR("Internal Server Error 500."),
	NO_RECORD_FOUND("Record with provided id is not found");
	
	private String errorMessage;

	private ErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
