package com.yzg.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("zuul-client1")
public interface ApiService {

	@RequestMapping("/")
	public String test();
}
