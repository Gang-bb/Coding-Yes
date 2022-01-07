package com.gangbb.contentcenter.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestService {

    @SentinelResource(value = "common", blockHandler = "failBlockHandler")
    public String common() {
        log.info("common....");
        return "common";
    }
}
