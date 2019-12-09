package com.yzg.spider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("urlmanage")
public interface UrlManageApiService {

	@RequestMapping("/")
	public String test();
}
