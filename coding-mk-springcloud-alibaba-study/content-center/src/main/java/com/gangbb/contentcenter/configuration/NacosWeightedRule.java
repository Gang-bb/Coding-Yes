package com.gangbb.contentcenter.configuration;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.cloud.nacos.ribbon.NacosServer;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.Server;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Gangbb
 * @date 2021/4/22 11:00
 * @Description: 扩展Ribbon-权重支持Nacos权重
 */
public class NacosWeightedRule extends AbstractLoadBalancerRule {
    @SuppressWarnings("all")
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(NacosWeightedRule.class);
    @Autowired
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    @Autowired
    private NacosServiceManager nacosServiceManager;

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
        // 读取配置文件，并初始化NacosWeightedRule
    }

    @Override
    public Server choose(Object key) {
        try {
            // ribbon的入口， 想要的参数都在里面
            BaseLoadBalancer loadBalancer = (BaseLoadBalancer) this.getLoadBalancer();
//        log.info("lb = {}", loadBalancer);
            // 想要请求的微服务的名称
            String name = loadBalancer.getName();
            // 拿到 Nacos的 服务发现的相关API
            NamingService namingService = nacosServiceManager.getNamingService(nacosDiscoveryProperties.getNacosProperties());
            // nacos client自动通过基于权重的负载均衡算法，给我们选择一个实例。
            Instance instance = namingService.selectOneHealthyInstance(name);
            log.info("选择的实例是：port = {}, instance = {}", instance.getPort(), instance);
            return new NacosServer(instance);
        } catch (NacosException e) {
            return null;
        }
    }
}
