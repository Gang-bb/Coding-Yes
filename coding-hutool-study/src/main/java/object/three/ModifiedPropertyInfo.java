package object.three;

import lombok.Data;

import java.io.Serializable;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
public class ModifiedPropertyInfo implements Serializable {

    // 对应的属性名
    private String property;

    // 未修改之前的值
    private Object oldValue;

    // 修改后的值
    private Object newValue;
}