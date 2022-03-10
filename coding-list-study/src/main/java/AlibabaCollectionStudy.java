import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Gangbb
 */
public class AlibabaCollectionStudy {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("all");
        list.add("dfg");
        list.add("bnm");
        list.add("cff");
        list.add("uii");
        list.add("uii");

        List<String> collect = list
                .stream() // 得到流
                .distinct() // 去重 5,2,1,3,4
                .sorted() // 自然排序 , 也可以自定义排序规则1,2,3,4,5
                .collect(Collectors.toList());//收集并返回

        System.out.println(list);
        Set<String> set = new HashSet<>(list);
        Set<String> tree = new TreeSet<>(list);
        System.out.println(set);
        System.out.println(tree);
        System.out.println(collect);
//
//        for (String item : list) {
//            if ("2".equals(item)) {
//                list.remove(item);
//            }
//        }
//        System.out.println(list);
//
//        ArrayList<String> users = new ArrayList<>(10);


//        Set<String> set = new HashSet<>(16);
//        set.add("1");
//        set.add("2");
//        System.out.println(set.en);
    }
}
