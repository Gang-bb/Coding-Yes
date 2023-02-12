package com.gangbb.concurrency.synchronizedTest;

/**
 * synchronized 锁方法快
 * 最典型的示例就是单例模式的下 双重检验锁实现
 *
 * @author lyx
 * @date 2023/2/12
 **/
public class LockBlock {

    public volatile static LockBlock uniqueInstance;

    private LockBlock(){}

    public static LockBlock getInstance(){
        if(uniqueInstance == null){
            synchronized (LockBlock.class){
                if(uniqueInstance == null){
                    uniqueInstance = new LockBlock();
                }
            }
        }
        return uniqueInstance;
    }

}
