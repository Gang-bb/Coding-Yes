package jisuan;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {
    public static void main(String[] args) {
        // 董秘个数
        int dmCount = 3;
        // 董事个数
        int dsCount = 3;
        // 董秘分数总和
        int dmTotal = 83;
        // 董事分数总和
        int dsTotal = 201;

        int finalCount = dmTotal/dmCount + dsTotal/dsCount;

        System.out.println(finalCount);
    }
}
