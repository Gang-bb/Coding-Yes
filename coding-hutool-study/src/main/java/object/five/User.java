package object.five;

import lombok.Data;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
public class User {
    @Compare("姓名")
    private String name;
    @Compare("年龄")
    private int age;

}