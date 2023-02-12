package com.gangbb.concurrency.synchronizedTest;

/**
 * synchronized 锁普通方法
 * 场景：避免卖票超卖
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class LockCommonMethod {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        Thread thread = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        };
        thread.start();


        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();

    }





}

class Ticket{
    public int ticketCount = 50;
    public synchronized void sale(){
        if(ticketCount > 0){
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (ticketCount--) + "张票。剩余：" + ticketCount);
        }
    }
}
