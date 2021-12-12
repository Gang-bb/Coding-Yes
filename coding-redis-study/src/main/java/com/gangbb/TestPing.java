package com.gangbb;

import redis.clients.jedis.Jedis;

/**
 * @author : Gangbb
 * @ClassName : TestPing
 * @Description :
 * @Date : 2021/2/4 9:06
 */
public class TestPing {
    public static void main(String[] args) {
        //1. new Jedis对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        // jedis中的命令跟官方命令一摸一样
        System.out.println(jedis.ping());
        System.out.println();
    }
}
