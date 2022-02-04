package com.gangbb.contentcenter.controller;


import com.alibaba.cloud.commons.lang.StringUtils;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.Tracer;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.context.ContextUtil;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gangbb.contentcenter.dao.content.ShareMapper;
import com.gangbb.contentcenter.domain.dto.UserDTO;
import com.gangbb.contentcenter.domain.entity.content.Share;
import com.gangbb.contentcenter.feignclient.TestBaiduFeignClient;
import com.gangbb.contentcenter.feignclient.TestUserCenterFeignClient;
import com.gangbb.contentcenter.rocketmq.MySource;
import com.gangbb.contentcenter.sentineltest.TestControllerBlockHandlerClass;
import com.gangbb.contentcenter.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import java.util.List;

/**
 * @author Gangbb
 * @date 2021/4/20 21:23
 * @Description:
 */
@RestController
@Slf4j
public class TestController {

    @Autowired(required = false)
    private ShareMapper shareMapper;

    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/test")
    public List<Share> testInsert() {
        // 1. 做插入
//        Share share = new Share();
//        share.setCreateTime(new Date());
//        share.setUpdateTime(new Date());
//        share.setTitle("xxx");
//        share.setCover("xxx");
//        share.setAuthor("大目");
//        share.setBuyCount(1);
//
//        this.shareMapper.insertSelective(share);
//
//        // 2. 做查询: 查询当前数据库所有的share  select * from share ;
//        List<Share> shares = this.shareMapper.selectAll();

//        return shares;
        return null;
    }

    /**
     * 测试：服务发现，证明内容中心总能找到用户中心
     *
     * @return 用户中心所有实例的地址信息
     */
    @GetMapping("test2")
    public List<ServiceInstance> getInstances() {
        // 查询指定服务的所有实例的信息
        // 用 consul/eureka/zookeeper...也能用这个方法
        return this.discoveryClient.getInstances("user-center");
    }

    @Autowired
    private TestUserCenterFeignClient testUserCenterFeignClient;

    @GetMapping("test-get")
    public UserDTO query(UserDTO userDTO) {
        return testUserCenterFeignClient.query(userDTO);
    }


    @Autowired
    private TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping("baidu")
    public String baiduIndex() {
        return this.testBaiduFeignClient.index();
    }

    @Autowired
    private TestService testService;

    @GetMapping("test-a")
    public String testA() {
        this.testService.common();
        return "test-a";
    }

    @GetMapping("test-b")
    public String testB() {
        this.testService.common();
        return "test-b";
    }


    @GetMapping("test-hot")
    @SentinelResource("hot")
    public String testHot(
            @RequestParam(required = false) String a,
            @RequestParam(required = false) String b
    ) {
        return a + " " + b;
    }

    @GetMapping("/test-sentinel-api")
    public String testSentinelAPI(
            @RequestParam(required = false) String a) {

        String resourceName = "test-sentinel-api";
        ContextUtil.enter(resourceName, "test-wfw");

        // 定义一个sentinel保护的资源，名称是test-sentinel-api
        Entry entry = null;
        try {

            entry = SphU.entry(resourceName);
            // 被保护的业务逻辑
            if (StringUtils.isBlank(a)) {
                throw new IllegalArgumentException("a不能为空");
            }
            return a;
        }
        // 如果被保护的资源被限流或者降级了，就会抛BlockException
        catch (BlockException e) {
            log.warn("限流，或者降级了", e);
            return "限流，或者降级了";
        } catch (IllegalArgumentException e2) {
            // 统计IllegalArgumentException【发生的次数、发生占比...】
            Tracer.trace(e2);
            return "参数非法！";
        } finally {
            if (entry != null) {
                // 退出entry
                entry.exit();
            }
            ContextUtil.exit();
        }
    }

    @GetMapping("/test-sentinel-resource")
    @SentinelResource(
            value = "test-sentinel-resource",
            blockHandler = "block",
            blockHandlerClass = TestControllerBlockHandlerClass.class,
            fallback = "fallback",
            fallbackClass = TestControllerBlockHandlerClass.class
    )
    public String testSentinelResource(@RequestParam(required = false) String a) {
        if (StringUtils.isBlank(a)) {
            throw new IllegalArgumentException("a cannot be blank.");
        }
        return a;
    }



    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test-rest-template-sentinel/{userId}")
    public UserDTO test(@PathVariable Integer userId) {
        return this.restTemplate
                .getForObject(
                        "http://user-center/users/{userId}",
                        UserDTO.class, userId);
    }

    @Autowired
    private Source source;


    @GetMapping("/test-stream")
    public String testStream() {
        this.source.output()
                .send(
                        MessageBuilder
                                .withPayload("消息体")
                                .build()
                );
        return "success";
    }

    @Autowired
    private MySource mySource;


    @GetMapping("/test-mySource")
    public String testMySource() {
        this.mySource.output()
                .send(
                        MessageBuilder
                                .withPayload("消息体")
                                .build()
                );
        return "success";
    }

}
