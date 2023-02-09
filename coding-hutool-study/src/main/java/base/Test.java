package base;

/**
 * 传值
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {
    public static void swap(int x, int y){
        int t;
        t = x;
        x = y;
        y = t;
        System.out.println(x + "," + y);
    }

    public static void main(String[] args) {
        Integer a = 3;
        Integer b = 4;
        Test.swap(a, b);
        System.out.println(a + "," + b);
    }
}
