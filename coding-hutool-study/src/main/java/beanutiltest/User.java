package beanutiltest;

import cn.hutool.core.annotation.Alias;

import java.util.Date;

/**
 * 用户测试类
 *
 * @author Gangbb
 * @date 2021/12/19
 **/
public class User {
    @Alias("userName")
    private String name;
    private Integer age;
    private Date birthday;
    private UserOtherInfo userOtherInfo;

    public UserOtherInfo getUserOtherInfo() {
        return userOtherInfo;
    }

    public void setUserOtherInfo(UserOtherInfo userOtherInfo) {
        this.userOtherInfo = userOtherInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}