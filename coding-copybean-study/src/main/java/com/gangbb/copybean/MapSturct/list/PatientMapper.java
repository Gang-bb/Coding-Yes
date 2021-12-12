package com.gangbb.copybean.MapSturct.list;

import com.gangbb.copybean.pojo.list.Patient;
import com.gangbb.copybean.pojo.list.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/10
 **/
@Mapper(componentModel = "spring")
public interface PatientMapper {
    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mapping(source = "name", target = "patientName")
    PatientDto toDto(Patient patient);
}
