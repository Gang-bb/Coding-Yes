package com.gangbb.concurrency.base;

/**
 * Java实现多线程方法--集成Thread类
 *
 * @author lyx
 * @date 2023/2/9
 **/
public class ThreadTest {

    public static void main(String[] args) {
        System.out.println("主线程运行：" + Thread.currentThread().getName());
        Task1 task = new Task1();
        task.start();

        Task2 task2 = new Task2();
        task2.start();
    }

    static class Task1 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("task1线程运行" + Thread.currentThread().getName());
                System.out.println("Task11继承Thread实现多线程");
            }
        }
    }

    static class Task2 extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("task2线程运行" + Thread.currentThread().getName());
                System.out.println("Task22继承Thread实现多线程");
            }
        }
    }
}
