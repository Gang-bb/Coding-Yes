package com.gangbb.copybean.MapSturct.list;

import com.gangbb.copybean.pojo.list.Doctor;
import com.gangbb.copybean.pojo.list.DoctorDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/10
 **/
@Mapper(uses = {PatientMapper.class})
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(source = "doctor.patientList", target = "patientDtoList")
    @Mapping(source = "doctor.specialty", target = "specialization")
    DoctorDto toDto(Doctor doctor);
}
