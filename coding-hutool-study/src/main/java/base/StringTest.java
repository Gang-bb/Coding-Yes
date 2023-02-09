package base;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class StringTest {
    public static void main(String[] args) {
//        String s =  "qq:ww:ee:*";
//        int indexOf = s.lastIndexOf(":");
//        String substring = s.substring(indexOf+1);
//        System.out.println(substring);

//        String a = "你好{name}";
//        HashMap<Object, Object> map = new HashMap<>();
//        map.put("name", null);
//        String format = StrUtil.format(a, map);
//        System.out.println(format);
        boolean result = false;
        Set<String> allCommonVariableNames = new HashSet<>();
        allCommonVariableNames.add("user");
        String user = "user";
//        allCommonVariableNames.forEach(name ->{
//        });
        List<String> split = StrUtil.split(user, ";");
        System.out.println(split);
    }
}
