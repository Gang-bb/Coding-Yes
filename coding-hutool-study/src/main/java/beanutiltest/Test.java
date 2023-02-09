package beanutiltest;
import beanutiltest.UserOtherInfo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;

import java.util.Date;

/**
 * 测试类
 *
 * @author Gangbb
 * @date 2021/12/19
 **/
public class Test {
    public static void main(String[] args) {
        User user = new User();
        UserOtherInfo userOtherInfo = new UserOtherInfo();
        userOtherInfo.setPhone("121212");
        userOtherInfo.setHeight(188);
        user.setUserOtherInfo(userOtherInfo);
        user.setName("");
        user.setAge(0);
        user.setBirthday(new Date());
        user.setName("zhangsan");

//        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
//
//        System.out.println(JSONUtil.parse(userVo));

        User user2 = new User();
        user2.setUserOtherInfo(new UserOtherInfo());
        user2.setName("www");
        user2.setAge(110);
        user2.setBirthday(new Date());

        System.out.println(JSONUtil.parse(user));


        BeanUtil.copyProperties(user, user2);

        System.out.println(JSONUtil.parse(user));
    }
}