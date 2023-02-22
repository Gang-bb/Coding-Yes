package utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;

/**
 * 代码生成
 *
 * @author lyx
 * @date 2023-02-17
 **/
public class Test {
    public static void main(String[] args) {
        String prefix = "mb";
        Field[] fields = ReflectUtil.getFields(MeetingSign.class);
        for (Field field : fields) {
            String fieldName = field.getName();
            String underlineCaseName = StrUtil.toUnderlineCase(fieldName);
            System.out.println(prefix + "." + underlineCaseName);
        }
    }
}
