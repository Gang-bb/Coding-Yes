package com.gangbb.contentcenter.service;

import com.gangbb.contentcenter.dao.content.ShareMapper;
import com.gangbb.contentcenter.domain.dto.UserDTO;
import com.gangbb.contentcenter.domain.entity.content.Share;
import com.gangbb.contentcenter.domain.vo.ShareVo;
import com.gangbb.contentcenter.feignclient.UserCenterFeignClient;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author Gangbb
 * @date 2021/4/21 12:16
 * @Description:
 */
@Service
public class ShareService {
    @Resource
    private ShareMapper shareMapper;

    @Resource
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private UserCenterFeignClient userCenterFeignClient;


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
