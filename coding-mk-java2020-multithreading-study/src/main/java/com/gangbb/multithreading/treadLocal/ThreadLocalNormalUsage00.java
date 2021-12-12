package com.gangbb.multithreading.treadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gangbb
 * @date 2021/3/31 12:37
 * @Description: 演示 2个线程分别使用自己的SimpleDateFormat
 */
public class ThreadLocalNormalUsage00 {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(10);
                System.out.println(date);
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                String date = new ThreadLocalNormalUsage00().date(104707);
                System.out.println(date);
            }
        }).start();
    }

    /**
     * 把秒转成日期输出
     * @param seconds
     * @return
     */
    public String date(int seconds) {
        //参数的单位是毫秒，从1970.1.1 00:00:00 GMT计时， 在这个基础上加传入的seconds
        Date date = new Date(1000 * seconds);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }
}
