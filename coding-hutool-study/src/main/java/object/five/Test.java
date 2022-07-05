package object.five;

/**
 * TODO:deprecated
 *
 * @author lyx
 * @date 2022/6/8
 **/
public class Test {

    public static void main(String[] args) {
        // 模拟一个保存前的数据对象
        User u1 = new User();
        u1.setName("大乔");
        u1.setAge(24);
        // 模拟一个保存后的数据对象
        User u2 = new User();
        u2.setName("小乔");
        u2.setAge(22);
        // 获取保存前后的字段变化情况
        String result = new CompareUtils<User>().compare(u1, u2);
        System.out.println(result);
    }

}