package com.CloudEureka.controller;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="CLIENT2-SERVICE")
public interface ClientFeignClient {

	@RequestMapping(value = "/client2/getAge/{account}", method = RequestMethod.POST)
	Integer getAge(@PathVariable("account") String inputAccount);
}
