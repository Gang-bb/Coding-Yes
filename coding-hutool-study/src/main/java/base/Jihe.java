package base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * ArrayList删除
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Jihe {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        list.add("bb");
        list.add("bb");
        list.add("cc");
        list.add("dd");
        ArrayList<String> list2 = null;
//        for (String s : list) {
//            if(s.equals("bb")){
//                list.remove("bb");
//            }
//        }
        for(Iterator<String> iterator = list.iterator(); iterator.hasNext();){
            System.out.println(iterator.next());
        }
    }
}
