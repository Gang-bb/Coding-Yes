package com.gangbb.dozerstudy.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.dozer.Mapping;

/**
 * @Author Gangbb
 * @Description User请求参数类
 * @Date 2021/8/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    // 与User的 loginName 字段名不同
    @Mapping("loginName")
    private String name;
    private String password;
    // 与User的 birthday 类型不同
    private String birthday;
    // 嵌套实体类
    private OtherInfo otherInfo;
}