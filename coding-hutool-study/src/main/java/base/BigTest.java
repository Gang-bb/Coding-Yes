package base;

import java.math.BigDecimal;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class BigTest {
    public static void main(String[] args) {
        BigDecimal num1 = new BigDecimal("4");
        BigDecimal num2 = new BigDecimal("7");
        BigDecimal divide = num2.divide(num1, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(divide);

    }
}
