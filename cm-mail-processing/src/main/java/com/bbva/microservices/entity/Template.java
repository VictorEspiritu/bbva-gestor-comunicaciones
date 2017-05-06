package com.bbva.microservices.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Template {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	private Date dateUpload;
	@Lob 
	@Column(name="CONTENT", length=2048)
	private String template;
	private Long idProject;
	private String nameProject;
	
	public Template() {
		// TODO Auto-generated constructor stub
	}
	
	public Template(String name, Date dateUpload, String template, Long idProject, String nameProject) {
		super();
		this.name = name;
		this.dateUpload = dateUpload;
		this.template = template;
		this.idProject = idProject;
		this.nameProject = nameProject;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateUpload() {
		return dateUpload;
	}
	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public Long getIdProject() {
		return idProject;
	}
	public void setIdProject(Long idProject) {
		this.idProject = idProject;
	}
	public String getNameProject() {
		return nameProject;
	}
	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"Template\": [ \"id\":\"");
		builder.append(id);
		builder.append("\", \"name\":\"");
		builder.append(name);
		builder.append("\", \"dateUpload\":\"");
		builder.append(dateUpload);
		builder.append("\", \"template\":\"");
		builder.append(template);
		builder.append("\", \"idProject\":\"");
		builder.append(idProject);
		builder.append("\", \"nameProject\":\"");
		builder.append(nameProject);
		builder.append("\"]}");
		return builder.toString();
	}
	
	
}
