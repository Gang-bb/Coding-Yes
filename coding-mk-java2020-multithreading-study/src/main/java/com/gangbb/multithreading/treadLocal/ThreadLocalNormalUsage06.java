package com.gangbb.multithreading.treadLocal;

/**
 * @author Gangbb
 * @date 2021/3/31 15:56
 * @Description: 演示ThreadLocal用法2：避免传递参数的麻烦
 */
public class ThreadLocalNormalUsage06 {

    public static void main(String[] args) {
        new Service1().process("");

    }
}

/**
 * 模拟Service请求1
 */
class Service1 {

    public void process(String name) {
        // 模拟Service1获取到用户信息
        User user = new User("Gangbb");
        // 把user信息保存到ThreadLocal
        UserContextHolder.holder.set(user);
        new Service2().process();
    }
}

/**
 * 模拟Service请求2
 */
class Service2 {

    public void process() {
        // Service2从ThreadLocal获取用户信息
        User user = UserContextHolder.holder.get();
        // Service2从ThreadLocal获取日期工具
        ThreadSafeFormatter.dateFormatThreadLocal.get();
        System.out.println("Service2拿到用户名：" + user.name);
        new Service3().process();
    }
}

class Service3 {

    public void process() {
        // Service3从ThreadLocal获取用户信息
        User user = UserContextHolder.holder.get();
        System.out.println("Service3拿到用户名：" + user.name);
        UserContextHolder.holder.remove();
    }
}

/**
 * ThreadLocal的持有类
 */
class UserContextHolder {

    public static ThreadLocal<User> holder = new ThreadLocal<>();


}

class User {

    String name;

    public User(String name) {
        this.name = name;
    }
}
