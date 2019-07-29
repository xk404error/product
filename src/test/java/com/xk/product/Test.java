package com.xk.product;

import java.math.BigDecimal;
import java.util.Arrays;

public class Test {
    public synchronized static  void test1(){
        System.out.println("static mo");
    }

    public static void main(String[] args) {
        BigDecimal  a=new BigDecimal("9600000");
        BigDecimal  b=new BigDecimal("16410");
        BigDecimal result = b.divide(a, 2, BigDecimal.ROUND_HALF_UP);
    }


}
