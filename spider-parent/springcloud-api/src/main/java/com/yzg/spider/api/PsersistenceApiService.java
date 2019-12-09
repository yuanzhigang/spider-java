package com.yzg.spider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("persistence")
public interface PsersistenceApiService {

	@RequestMapping("/")
	public String test();
}
