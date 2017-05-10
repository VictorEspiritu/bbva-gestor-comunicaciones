package com.bbva.microservices.componet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bbva.microservices.entity.Template;
import com.bbva.microservices.repository.TemplateRepository;

@Component
public class TemplateComponent {

	private TemplateRepository templateRepository;

	@Autowired
	public TemplateComponent(TemplateRepository templateRepository) {
		this.templateRepository = templateRepository;
	}

	public void addTemplate(Template template) {
		templateRepository.save(template);
	}

	public Template getTemplate(Long idTemplate) {
		return templateRepository.findOne(idTemplate);
	}

}
