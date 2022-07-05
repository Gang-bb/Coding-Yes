package object.three;

import lombok.Data;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
public class PropertyModelInfo {
    //属性名
    private String propertyName;
    // 属性值
    private Object value;
    // 返回值类型
    private Class<?> returnType;
}