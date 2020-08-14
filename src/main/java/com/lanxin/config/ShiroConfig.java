package com.lanxin.config;

import com.lanxin.CustomRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    private String test;

    private String hello;
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        redisManager.setExpire(1800);
        return redisManager;

    }

    @Bean
    public RedisCacheManager cacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManagers=new RedisCacheManager();
        redisCacheManagers.setRedisManager(redisManager);
        return redisCacheManagers;

    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String,String> h=new HashMap<>();
        h.put("/login","anon");
        h.put("/loginot","anon");
       h.put("/**","authc");
       shiroFilterFactoryBean.setFilterChainDefinitionMap(h);
       shiroFilterFactoryBean.setLoginUrl("/unk");
        return shiroFilterFactoryBean;
    }

    @Bean//加密
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(10);
        return hashedCredentialsMatcher;
    }
    @Bean
    public CustomRealm customRealm(HashedCredentialsMatcher hashedCredentialsMatcher){
        CustomRealm customRealm=new CustomRealm();
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return customRealm;
    }

    @Bean
   public SecurityManager defaultWebSecurityManager(CustomRealm customRealm,RedisCacheManager redisCacheManager,DefaultWebSessionManager sessionManager){
       DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
       defaultWebSecurityManager.setRealm(customRealm); //设置realm
     defaultWebSecurityManager.setCacheManager(redisCacheManager);//自定义缓存 使用redis
        defaultWebSecurityManager.setSessionManager(sessionManager);
       return  defaultWebSecurityManager;
   }


   @Bean
   public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator d=new DefaultAdvisorAutoProxyCreator();
        d.setProxyTargetClass(true);
        return d;
   }

   @Bean//开启shiro aop注解支持
   public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager defaultWebSecurityManager){
        AuthorizationAttributeSourceAdvisor a=new AuthorizationAttributeSourceAdvisor();
        a.setSecurityManager(defaultWebSecurityManager);
        return a;
   }

   @Bean
 public DefaultWebSessionManager sessionManager(RedisSessionDAO redisSessionDAO){
        DefaultWebSessionManager defaultWebSessionManager=new DefaultWebSessionManager();
        defaultWebSessionManager.setSessionDAO(redisSessionDAO);
        return defaultWebSessionManager;
 }

 @Bean
 public RedisSessionDAO sessionDAO(RedisManager redisManager){
        RedisSessionDAO redisSessionDAO=new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager);
        return redisSessionDAO;
 }
}
