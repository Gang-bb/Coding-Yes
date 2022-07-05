package object.four;

import lombok.Data;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/

@Data
public class ComparbleResult {

    /**
     * 变更字段
     */
    private String fieldName;

    /**
     * 变更前类的内容容
     */
    private Object fieldContent;
    /**
     * 变更后类的内容容
     */
    private Object newFieldContent;
    /**
     * 变更的枚举类型
     */
    private String handerType;

}