package com.gangbb.concurrency.future;

import java.util.concurrent.*;

/**
 *  演示get的超时方法，需要注意超时后处理，调用future.cancel()。演示cancel传入true和false的区别，代表是否中断正在执行的任务。
 *
 * @author lyx
 * @date 2023/2/13
 **/
public class Timeout {

    private static final Ad DEFAULT_AD = new Ad("无网络时候的默认广告");
    private static final ExecutorService exec = Executors.newFixedThreadPool(10);

    static class Ad {

        String name;

        public Ad(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Ad{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class TimeoutTask implements Callable<Ad> {

        @Override
        public Ad call() throws Exception {
            try{
                // 模拟处理任务
                for (int i = 0; i < 3; i++) {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println("task处理任务中：" + i);
                }
            }catch (InterruptedException e){
                System.out.println("task方法执行内被中断了");
                return new Ad("中断默认广告777");
            }
            return new Ad("task执行成功广告");
        }
    }

    public void printAd() {
        Future<Ad> f = exec.submit(new TimeoutTask());
        Ad ad;
        try {
            ad = f.get(6100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            ad = new Ad("被中断时候的默认广告");
        } catch (ExecutionException e) {
            ad = new Ad("异常时候的默认广告");
        } catch (TimeoutException e) {
            ad = new Ad("超时时候的默认广告");
            System.out.println("超时，未获取到广告");
            boolean cancel = f.cancel(true);
            System.out.println("cancel的结果：" + cancel);
        }
        exec.shutdown();
        System.out.println(ad);
    }

    public static void main(String[] args) {
        Timeout timeout = new Timeout();
        timeout.printAd();
    }
}
