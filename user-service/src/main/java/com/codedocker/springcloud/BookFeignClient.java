package com.codedocker.springcloud;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.Map;

@FeignClient(name = "node-sidecar")
public interface BookFeignClient {
    @RequestMapping("/api/public/health.json")
    public Map<String,Object> getStatus();
}
