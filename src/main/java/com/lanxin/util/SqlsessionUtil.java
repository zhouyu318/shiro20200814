package com.lanxin.util;

import com.baomidou.mybatisplus.core.MybatisSqlSessionFactoryBuilder;
import com.lanxin.dao.Shirodao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class SqlsessionUtil {

    public static SqlSession getsql(){
        MybatisSqlSessionFactoryBuilder mybatisSqlSessionFactoryBuilder=new MybatisSqlSessionFactoryBuilder();
        try {
            return mybatisSqlSessionFactoryBuilder.build(Resources.getResourceAsReader("mybatisconfig.xml")).openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Shirodao shirodao=SqlsessionUtil.getsql().getMapper(Shirodao.class);
         String pass=shirodao.passwordbyname("admin");
        System.out.println(pass);
    }
}
