package com.bbva.microservices.dto;

public class Response {

	private Integer responseCode;
	private String message;
	
	public Response() {
		// TODO Auto-generated constructor stub
	}

	public Integer getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"Response\": [ \"responseCode\":\"");
		builder.append(responseCode);
		builder.append("\", \"message\":\"");
		builder.append(message);
		builder.append("\"]}");
		return builder.toString();
	}
	
	
}
