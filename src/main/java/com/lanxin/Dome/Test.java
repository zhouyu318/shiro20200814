package com.lanxin.Dome;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Test {
    public static void main(String[] args) {
        Md5Hash m=new Md5Hash("123456","",10);

        System.out.println(m);
    }
}
