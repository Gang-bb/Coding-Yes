package com.gangbb.copybean.MapSturct;

import com.gangbb.copybean.pojo.User3;
import com.gangbb.copybean.pojo.UserRequest3;
import org.mapstruct.Mapper;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Mapper(componentModel = "spring")
public interface Use3Mapper {
    // Use3Mapper INSTANCE = Mappers.getMapper(Use3Mapper.class);

    User3 user2userRequest(UserRequest3 userRequest);
}
