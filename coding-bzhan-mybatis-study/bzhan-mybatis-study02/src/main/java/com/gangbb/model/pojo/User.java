package com.gangbb.model.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author : Gangbb
 * @ClassName : User
 * @Description :
 * @Date : 2021/2/5 15:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Alias(value = "u")
public class User {
    private Integer id;
    private String name;
    private String password;
}
