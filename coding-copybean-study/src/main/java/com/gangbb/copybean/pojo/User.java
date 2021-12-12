package com.gangbb.copybean.pojo;

import com.gangbb.copybean.MapSturct.BaseModelMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author Gangbb
 * @Description User实体类
 * @Date 2021/8/7
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -5308107267346753671L;
    private Integer id;
    private String loginName;
    private String password;
    private Date birthday;
    private OtherInfo otherInfo;

    @Mapper(componentModel = "spring")
    public interface UserMapper extends BaseModelMapper<UserRequest, User> {

        @Override
        User toModel(UserRequest userRequest);

        //User user2userRequest(UserRequest userRequest);
    }

    public static final UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}