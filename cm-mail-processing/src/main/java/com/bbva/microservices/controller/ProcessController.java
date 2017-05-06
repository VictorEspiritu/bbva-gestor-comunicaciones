package com.bbva.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bbva.microservices.componet.MailComponent;
import com.bbva.microservices.entity.Template;


@RestController
@RequestMapping("/mail")
public class ProcessController {
	
	private MailComponent mailComponent;
		
	@Autowired
	public ProcessController(MailComponent mailComponent) {
		this.mailComponent = mailComponent;
	}
	
	@RequestMapping( value="/status", method=RequestMethod.GET)
	String getSatus(){
		return "OK";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST)
	String getSatus(@RequestBody Template template){
		mailComponent.addTemplate(template);
		return "OK";
	}
	
	@RequestMapping( value="/get/{id}", method=RequestMethod.GET)
	Template getSatus(@PathVariable Long id){
		Template mail = mailComponent.getTemplate(id);
		return mail;
	}
}
