package stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * peek()测试
 *
 * @author lyx
 * @date 2023-02-26
 **/
public class PeekTest {

    public static void main(String[] args) {
        Apple[] appleArray = {
                new Apple("blue", 99),
                new Apple("blue", 22),
                new Apple("blue", 44),
                new Apple("red", 46),
                new Apple("red", 78)
        };

        List<Apple> appleList = Arrays.asList(appleArray);

        List<Apple> collect = appleList.stream().peek(System.out::println)
                .peek(apple -> apple.setWeight(99))
                .peek(System.out::println)
                .collect(Collectors.toList());

    }
    static class Apple {
        private String color;
        private int weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
