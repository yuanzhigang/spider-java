package com.yzg.spider.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("html")
public interface HtmlApiService {

	@RequestMapping("/")
	public String test();
}
