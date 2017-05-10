package com.bbva.microservices.dto;

import java.util.List;

public class Email {
	
	private String from;
	private List<String> to;
	private List<String> cc;
	private List<String> bcc;
	private String subject;
	private String htmlContent;
	
	public Email() {
		// TODO Auto-generated constructor stub
	}

	public Email(String from, List<String> to, List<String> cc, List<String> bcc, String subject, String htmlContent) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.htmlContent = htmlContent;
	}



	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public List<String> getTo() {
		return to;
	}

	public void setTo(List<String> to) {
		this.to = to;
	}

	public List<String> getCc() {
		return cc;
	}

	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public List<String> getBcc() {
		return bcc;
	}

	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"Email\" = { \"from\":\"");
		builder.append(from);
		builder.append("\", \"to\":\"");
		builder.append(to);
		builder.append("\", \"cc\":\"");
		builder.append(cc);
		builder.append("\", \"bcc\":\"");
		builder.append(bcc);
		builder.append("\", \"subject\":\"");
		builder.append(subject);
		builder.append("\", \"htmlContent\":\"");
		builder.append(htmlContent);
		builder.append("\"}");
		return builder.toString();
	}

	
}	
