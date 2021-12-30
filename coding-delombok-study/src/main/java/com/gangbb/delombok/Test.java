package com.gangbb.delombok;

/**
 * TODO:deprecated
 *
 * @author Gangbb
 * @date 2021/12/30
 **/
public class Test {
    public static void main(String[] args) {
        User user = User.builder().name("12").email("pp").build();
        System.out.println(user);

    }
}