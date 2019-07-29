package com.xk.product;

public class Test2 {
    public static void main(String[] args) {
        Test test = null;
        test.test1();
        System.out.println("a"+"b"=="ab");
        String a="a";
        String aa="a";
        String b="b";
        String ab="ab";
        String aandb=a+b;
        System.out.println(aa==a);
        System.out.println(a+b=="ab");
        System.out.println(a+b==ab);
        System.out.println(aandb=="ab");
    }
}
