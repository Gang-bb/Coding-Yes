package com.gangbb.copybean.pojo.list;

import lombok.Data;

import java.util.List;

/**
 * @Author Gangbb
 * @Description 医生业务传输类
 * @Date 2021/8/10
 **/
@Data
public class DoctorDto {
    private int id;
    private String name;
    private String specialization;
    private List<PatientDto> patientDtoList;

}