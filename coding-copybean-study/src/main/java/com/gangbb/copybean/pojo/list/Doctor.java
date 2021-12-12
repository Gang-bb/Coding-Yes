package com.gangbb.copybean.pojo.list;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @Author Gangbb
 * @Description 医生类(含有Patient列表)
 * @Date 2021/8/10
 **/
@Data
@AllArgsConstructor
public class Doctor {
    private Integer id;
    private String name;
    private String specialty;
    private List<Patient> patientList;
}