package stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 求每种颜色苹果的平均重量
 *
 * @author lyx
 * @date 2023-02-26
 **/
public class StreamTest2 {

    public static void main(String[] args) {
        Apple[] appleArray = {
                new Apple("blue", 99),
                new Apple("blue", 22),
                new Apple("blue", 44),
                new Apple("red", 46),
                new Apple("red", 78)
        };

        List<Apple> appleList = Arrays.asList(appleArray);

        Map<String, Integer> result = new HashMap<>();
        appleList.stream()
                .collect((Collectors.groupingBy(Apple::getColor, Collectors.averagingInt(Apple::getWeight)))
                ).forEach((k, v) -> result.put(k, v.intValue()));

        System.out.println(result);
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
    }
}
