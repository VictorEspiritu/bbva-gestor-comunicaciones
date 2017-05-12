package com.bbva.microservices.proxy;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bbva.microservices.dto.Email;
import com.bbva.microservices.dto.Response;

@FeignClient(name="mail-proxy", url="esev.machine.container:5550/test")
public interface MailServiceProxy {

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	Response postEmail(@RequestBody Email email);
}
