package com.gangbb.copybean.pojo.list;

import com.gangbb.copybean.MapSturct.list.DoctorMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Gangbb
 * @Description TODO
 * @Date 2021/8/10
 **/
@SpringBootTest
@Slf4j
public class DoctorTest {
    @Test
    public void testList(){
        Patient patient1 = new Patient(1, "病人1");
        Patient patient2 = new Patient(2, "病人2");
        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient2);
        Doctor doctor = new Doctor(1, "医生", "xxx", patientList);
        DoctorDto doctorDto = DoctorMapper.INSTANCE.toDto(doctor);
        log.info(doctorDto.toString());
    }

}