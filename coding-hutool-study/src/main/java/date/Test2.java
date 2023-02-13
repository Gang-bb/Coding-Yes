package date;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 获取指定年月所有周末日期
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        DateTime parse = DateUtil.parse("2023-01-01 00:02:00", "yyyy-MM-dd HH:mm");
        boolean before = parse.isAfter(new Date());
        LocalDateTime of = LocalDateTime.of(2022, 2, 24, 1, 1);
        System.out.println(of.isAfter(LocalDateTime.now()));
        System.out.println(parse);
//        DateTime dateTime = DateUtil.beginOfMonth(DateUtil.now());
//        System.out.println(dateTime);
//        while (dateTime.getTime() < DateUtil.date().getTime()) {
//            list.add(DateUtil.format(dateTime, "yyyy-mm-dd"));
//            dateTime = DateUtil.offsetDay(dateTime, 1);
//        }
        System.out.println(before);
    }
}
