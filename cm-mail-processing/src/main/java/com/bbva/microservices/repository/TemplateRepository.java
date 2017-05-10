package com.bbva.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bbva.microservices.entity.Template;

public interface TemplateRepository extends JpaRepository<Template, Long>{

}
