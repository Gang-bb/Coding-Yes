package com.gangbb.contentcenter.service;

import com.gangbb.contentcenter.dao.content.ShareMapper;
import com.gangbb.contentcenter.domain.dto.ShareAuditDTO;
import com.gangbb.contentcenter.domain.dto.UserDTO;
import com.gangbb.contentcenter.domain.dto.messaging.UserAddBonusMsgDTO;
import com.gangbb.contentcenter.domain.entity.content.Share;
import com.gangbb.contentcenter.domain.vo.ShareVo;
import com.gangbb.contentcenter.feignclient.UserCenterFeignClient;
import lombok.RequiredArgsConstructor;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * @author Gangbb
 * @date 2021/4/21 12:16
 * @Description:
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {

    private final ShareMapper shareMapper;

    private final RestTemplate restTemplate;


    private final DiscoveryClient discoveryClient;

    private final UserCenterFeignClient userCenterFeignClient;

    private final RocketMQTemplate rocketMQTemplate;



    public Share auditById(Integer id, ShareAuditDTO auditDTO) {
        // 1. 查询share是否存在，不存在或者当前的audit_status != NOT_YET，那么抛异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在！");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过！");
        }

        // 2.审核资源，将状态设为PASS/REJECT
        share.setAuditStatus(auditDTO.getAuditStatusEnum().toString());
        this.shareMapper.updateByPrimaryKey(share);

        // 3. 如果是PASS，那么发送消息给rocketmq，让用户中心去消费，并为发布人添加积分
        this.rocketMQTemplate.convertAndSend("add-bonus",
                UserAddBonusMsgDTO.builder()
                        .userId(share.getUserId())
                        .bonus(50).build());
//        if (AuditStatusEnum.PASS.equals(auditDTO.getAuditStatusEnum())) {
//            // 发送半消息。。
//            String transactionId = UUID.randomUUID().toString();
//
//            this.source.output()
//                .send(
//                    MessageBuilder
//                        .withPayload(
//                            UserAddBonusMsgDTO.builder()
//                                .userId(share.getUserId())
//                                .bonus(50)
//                                .build()
//                            )
//                            // header也有妙用...
//                        .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
//                        .setHeader("share_id", id)
//                        .setHeader("dto", JSON.toJSONString(auditDTO))
//                        .build()
//            );
//        }
//        else {
//            this.auditByIdInDB(id, auditDTO);
//        }
        return share;
    }


    /**
     * 使用Feign调用
     * @param id
     * @return
     */
    public ShareVo findById(Integer id){

        // 通过share_id找到内容分享记录
        Share share = shareMapper.selectByPrimaryKey(id);

        // 获取发布人id
        Integer userId = share.getUserId();


        // 找到发布人信息
        UserDTO userDto = this.userCenterFeignClient.findById(userId);

        // 信息装配
        ShareVo shareVo = new ShareVo();
        BeanUtils.copyProperties(share, shareVo);
        shareVo.setWxNickname(userDto.getWxNickname());

        return shareVo;
    }


    /**
     * 使用ribbon负载均衡后
     * @param id
     * @return
     */
//    public ShareVo findById(Integer id){
//
//        // 通过share_id找到内容分享记录
//        Share share = shareMapper.selectByPrimaryKey(id);
//
//        // 获取发布人id
//        Integer userId = share.getUserId();
//
//
//        // 找到发布人信息
//        UserDto userDto = this.restTemplate.getForObject("http://user-center/users/{userId}", UserDto.class, userId);
//
//        // 信息装配
//        ShareVo shareVo = new ShareVo();
//        BeanUtils.copyProperties(share, shareVo);
//        shareVo.setWxNickname(userDto.getWxNickname());
//
//        return shareVo;
//    }

    /**
     *  使用随机负载均衡算法
     * @param id
     * @return
     */
//    public ShareVo findById(Integer id){
//
//        // 通过share_id找到内容分享记录
//        Share share = shareMapper.selectByPrimaryKey(id);
//
//        // 获取发布人id
//        Integer userId = share.getUserId();
//
//        // 获取用户中心所有实例的信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//
//        // 获取用户中心所有实例的请求地址
//        List<String> urls = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/users/{id}")
//                .collect(Collectors.toList());
//
//        // 随机获取一个请求的下标
//        int index = ThreadLocalRandom.current().nextInt(urls.size());
//
//        // 找到发布人信息
//        UserDto userDto = this.restTemplate.getForObject(urls.get(index), UserDto.class, userId);
//
//        // 信息装配
//        ShareVo shareVo = new ShareVo();
//        BeanUtils.copyProperties(share, shareVo);
//        shareVo.setWxNickname(userDto.getWxNickname());
//
//        return shareVo;
//    }

    /**
     *  引入spring cloud alibaba 后
     * @param id
     * @return
     */
//    public ShareVo findById(Integer id){
//
//        // 通过share_id找到内容分享记录
//        Share share = shareMapper.selectByPrimaryKey(id);
//
//        // 获取发布人id
//        Integer userId = share.getUserId();
//
//        // 获取用户中心所有实例的信息
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
//
//        String url = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/users/{id}")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例"));
//
//
//        // 找到发布人信息
//        UserDto userDto = this.restTemplate.getForObject(url, UserDto.class, userId);
//
//        // 信息装配
//        ShareVo shareVo = new ShareVo();
//        BeanUtils.copyProperties(share, shareVo);
//        shareVo.setWxNickname(userDto.getWxNickname());
//
//        return shareVo;
//    }

    /**
     * 引入spring cloud alibaba 前
     * @param id
     * @return
     */
//    public ShareVo findById(Integer id){
//
//        // 通过share_id找到内容分享记录
//        Share share = shareMapper.selectByPrimaryKey(id);
//
//        // 获取发布人id
//        Integer userId = share.getUserId();
//
//        // 找到发布人信息
//        UserDto userDto = this.restTemplate.getForObject("http://localhost:8080/users/{id}", UserDto.class, userId);
//
//        // 信息装配
//        ShareVo shareVo = new ShareVo();
//        BeanUtils.copyProperties(share, shareVo);
//        shareVo.setWxNickname(userDto.getWxNickname());
//
//        return shareVo;
//    }
}
