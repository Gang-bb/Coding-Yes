package com.gangbb.copybean.MapSturct;

import com.gangbb.copybean.MapSturct.util.DateMapper;
import com.gangbb.copybean.pojo.util.TestUtil;
import com.gangbb.copybean.pojo.util.TestUtilVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/9
 **/
@Mapper(uses = {DateMapper.class})
public interface UtilMapper {
    UtilMapper INSTANCE = Mappers.getMapper(UtilMapper.class);

    @Mapping(target = "addTime", source = "addTime", qualifiedByName = "dateTime2String")
    TestUtilVO toVO(TestUtil testUtil);
}
