package date;

import cn.hutool.core.date.DateUtil;

/**
 * TODO:deprecated
 *
 * @author Gangbb
 * @date 2022/1/3
 **/
public class Test {
    public static void main(String[] args) {
        String date = "2000-01-01 00:00:00";
        String date2 = "2000-01-01";
        System.out.println(DateUtil.parse(date));
        System.out.println(DateUtil.parse(date2));
        System.out.println(DateUtil.format(DateUtil.parse(date), "yyyy-MM-dd"));
        System.out.println(DateUtil.format(DateUtil.parse(date2), "yyyy-MM-dd HH:mm:ss"));
    }
}