package liebiao;

import cn.hutool.core.collection.CollUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2023/2/14
 **/
public class Subtract {
    public static void main(String[] args) {
        List<Object> list1 = new ArrayList<>(3);
        list1.add("manager");
        list1.add("leader");
        list1.add("secretary");

        List<Object> list2 = new ArrayList<>(3);
        list2.add("manager");
        list2.add("leader");
        list2.add("secretary");
         Collection<Object> subtract = CollUtil.subtract(list1, list2);
        System.out.println(subtract);
        list1 = (List<Object>) CollUtil.subtract(list1, list2);
        System.out.println(list1);
    }
}
