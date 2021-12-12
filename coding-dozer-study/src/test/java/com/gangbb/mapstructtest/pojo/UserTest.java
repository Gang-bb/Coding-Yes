package com.gangbb.mapstructtest.pojo;

import cn.hutool.core.date.DateUtil;
import com.gangbb.dozerstudy.dozer.DozerMapper;
import com.gangbb.dozerstudy.pojo.OtherInfo;
import com.gangbb.dozerstudy.pojo.User;
import com.gangbb.dozerstudy.pojo.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/7
 **/
@SpringBootTest
@Slf4j
public class UserTest {

    @Test
    public void testSingle(){
        // 初始化UserRequest
        UserRequest userRequest =
                new UserRequest(1, "名字", "密码", DateUtil.today(), new OtherInfo("备注"));

        // set and get
        User user1 = new User();
        long startTime1 = System.currentTimeMillis();
        user1.setId(userRequest.getId());
        user1.setLoginName(userRequest.getName());
        user1.setPassword(userRequest.getPassword());
        user1.setBirthday(DateUtil.parse(userRequest.getBirthday(), "yyyy-MM-dd"));
        user1.setOtherInfo(userRequest.getOtherInfo());
        log.info(user1.toString());
        log.info("set and get，耗时(ms):{}", System.currentTimeMillis() - startTime1);

        // spring.BeanUtils
        User user2 = new User();
        long startTime2 = System.currentTimeMillis();
        BeanUtils.copyProperties(userRequest, user2);
        log.info(user2.toString());
        log.info("spring.BeanUtils，耗时(ms):{}", System.currentTimeMillis() - startTime2);

        // dozer
        User user3 = new User();
        long startTime3 = System.currentTimeMillis();
        DozerMapper.get().map(userRequest, user3);
        log.info(user2.toString());
        log.info("dozer，耗时(ms):{}", System.currentTimeMillis() - startTime3);

    }

    private User setAndGet(UserRequest userRequest){
        return new User(userRequest.getId(),
                        userRequest.getName(),
                        userRequest.getPassword(),
                        DateUtil.parse(userRequest.getBirthday()),
                        userRequest.getOtherInfo());
    }
}