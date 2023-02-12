package com.gangbb.concurrency.base;

/**
 * Java实现多线程方法--实现Runnable接口
 *
 * @author lyx
 * @date 2023/2/9
 **/
public class RunnableTest {
    public static void main(String[] args) {
        Task task = new Task();
        Thread thread  = new Thread(task);
        thread.run();
        thread.start();
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("task1线程运行" + Thread.currentThread().getName());
                System.out.println("Task11实现Runnable接口实现多线程");
            }
        }
    }
}
