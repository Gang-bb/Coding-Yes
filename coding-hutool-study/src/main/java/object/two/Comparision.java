package object.two;

import lombok.Data;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
public class Comparision {

    //字段
    private String field;
    //字段旧值
    private Object before;
    //字段新值
    private Object after;

}
