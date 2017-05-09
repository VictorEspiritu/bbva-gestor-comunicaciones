package com.bbva.microservices.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Message {

	private String id;
	private Date dateIn;
	private Integer typeMessage;
	private String channel;
	private Integer priority;
	private Long idTemplate;
	private Mail mail;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	public Message(Date dateIn, Integer typeMessage, String channel, Integer priority, Long idTemplate,
			Mail mail) {
		super();
		this.dateIn = dateIn;
		this.typeMessage = typeMessage;
		this.channel = channel;
		this.priority = priority;
		this.idTemplate = idTemplate;
		this.mail = mail;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getDateIn() {
		return dateIn;
	}
	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}
	public Integer getTypeMessage() {
		return typeMessage;
	}
	public void setTypeMessage(Integer typeMessage) {
		this.typeMessage = typeMessage;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Long getIdTemplate() {
		return idTemplate;
	}
	public void setIdTemplate(Long idTemplate) {
		this.idTemplate = idTemplate;
	}
	public Mail getMail() {
		return mail;
	}
	public void setMail(Mail mail) {
		this.mail = mail;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\"Message\" = { \"id\":\"");
		builder.append(id);
		builder.append("\", \"dateIn\":\"");
		builder.append(dateIn);
		builder.append("\", \"typeMessage\":\"");
		builder.append(typeMessage);
		builder.append("\", \"channel\":\"");
		builder.append(channel);
		builder.append("\", \"priority\":\"");
		builder.append(priority);
		builder.append("\", \"idTemplate\":\"");
		builder.append(idTemplate);
		builder.append("\", \"mail\":\"");
		builder.append(mail);
		builder.append("\"}");
		return builder.toString();
	}

	
	
	
}
