package com.gangbb.concurrency.base;

/**
 * 同时用 Thread 和 Runnable 两种的情况
 *
 * @author lyx
 * @date 2023-02-22
 **/
public class BothThreadAndRunnable {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("实现Runnable的run方法");
            }
        }){
            @Override
            public void run() {
                System.out.println("匿名内部类,重写Thread类的run方法");
            }
        }.run();
    }
}
