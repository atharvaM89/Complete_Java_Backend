package com.avm.Notification_Api.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductAPI {

    @GetMapping("/place")
    public String invokeProductAPI();
}
