package com.gangbb.copybean.MapSturct;

import com.gangbb.copybean.pojo.User;
import com.gangbb.copybean.pojo.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @Author Gangbb
 * @Description MapStruct
 * @Date 2021/8/8
 **/
@Mapper
public interface ConvertMapper {

    @Mappings({
            @Mapping(source = "name", target = "loginName"),
            @Mapping(source = "birthday", target = "birthday", dateFormat = "yyyy-MM-dd HH:mm:ss"),
    })
    User user2userRequest(UserRequest userRequest);
}
