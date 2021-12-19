package beanutiltest;

import java.util.Date;

/**
 * UserVo
 *
 * @author Gangbb
 * @date 2021/12/19
 **/
public class UserVo {

    private String userName;
    private Integer age;
    private Date birthday;
    private UserOtherInfo userOtherInfo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public UserOtherInfo getUserOtherInfo() {
        return userOtherInfo;
    }

    public void setUserOtherInfo(UserOtherInfo userOtherInfo) {
        this.userOtherInfo = userOtherInfo;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", userOtherInfo=" + userOtherInfo +
                '}';
    }
}