package object.one;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
@Accessors(chain = true) //注解用来配置lombok如何产生和显示getters和setters的方法
public class ApIData {

    /**
     * 身份证号
     */
    private String ident_card;

    /**
     * 姓名
     */
    private String name;

    /**
     * 户号  水务局使用查询
     */
    private String hh;

    /**
     * 用水月份  YYYY-MM
     */
    private String month;

    /**
     * 房东用户ID
     */
    private String owner_id;

    /**
     * 所属街道
     */
    private String street_name;

}
