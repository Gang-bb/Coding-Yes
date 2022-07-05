package object.one;

import lombok.Data;

/**
 * 比较结果类
 *
 * @author lyx
 * @date 2022/6/8
 **/
@Data
public class Comparison {
    private String field;
    private Object before;
    private Object after;
    private Boolean isUpdate;
}
