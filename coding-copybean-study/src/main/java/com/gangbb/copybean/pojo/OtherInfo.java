package com.gangbb.copybean.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Gangbb
 * @Description User实体的 嵌套实体类
 * @Date 2021/8/7
 **/
@Data
@AllArgsConstructor
public class OtherInfo implements Serializable {
    private static final long serialVersionUID = -6035283631307416845L;
    private String remark;
}