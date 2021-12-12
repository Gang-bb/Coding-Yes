import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author LiangYiXiang
 * @Description 测试创建列表
 * @Date 2021/11/13
 **/
public class CreateListTest {
    public static void main(String[] args) {
        way4();
    }

    public static void way1(){
        List<String> list = Arrays.asList("1", "2");
        //对转换后的list插入一条数据
        list.add("3");
        System.out.println(list);
    }

    public static void way2(){
        ArrayList<String> list = new ArrayList<String>(Arrays.asList("1", "2")) ;
        //对转换后的list插入一条数据
        list.add("3");
        System.out.println(list);
    }

    public static void way3(){
        String[] strArray = {"1", "2"};
        ArrayList< String> arrayList = new ArrayList<String>(strArray.length);
        Collections.addAll(arrayList, strArray);
        arrayList.add("3");
        System.out.println(arrayList);
    }

    public static void way4(){
        // String数组
        String[] arrays = {"a", "b", "c"};
        List<String> stringList= Stream.of(arrays).collect(Collectors.toList());
        // int[]、long[]、 double[]
        List<Integer> intList= Arrays.stream(new int[] { 1, 2, 3, }).boxed().collect(Collectors.toList());
        List<Long> longList= Arrays.stream(new long[] { 1, 2, 3 }).boxed().collect(Collectors.toList());
        List<Double> doubleList= Arrays.stream(new double[] { 1, 2, 3 }).boxed().collect(Collectors.toList());


        System.out.println(stringList);
        System.out.println(intList);
        System.out.println(longList);
        System.out.println(doubleList);
    }
}
