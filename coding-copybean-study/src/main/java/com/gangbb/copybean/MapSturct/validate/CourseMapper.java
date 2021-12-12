package com.gangbb.copybean.MapSturct.validate;

import org.mapstruct.Mapper;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/10
 **/
@Mapper(uses = {Validator.class}, componentModel = "spring")
public interface CourseMapper {

    CourseVO toVO(Course course) throws RuntimeException;
}
