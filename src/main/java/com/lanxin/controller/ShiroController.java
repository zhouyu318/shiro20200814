package com.lanxin.controller;

import com.lanxin.LanxinResult.Result;
import com.lanxin.LanxinResult.Test;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShiroController {

    @RequestMapping("login")
    public Result login(String username,String password){
        Subject subject= SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return  Result.ok("登录成功");
        } catch (IncorrectCredentialsException e) {
            throw new Test(500,"密码错误");
        }
        catch (AuthenticationException e) {
            throw new Test(500,"该用户不存在");
        }
        catch (Exception e) {
            throw new Test(500,"登录失败");
        }

    }

    @RequestMapping("loginout")
    public String loginout(){

        return "退出";
    }

    @RequestMapping("unk")
    public Result unk(){

        return Result.ok("你需要登录");
    }
    @RequiresPermissions("add")
    @RequestMapping("add")
    public String add(){

        return "添加";
    }
    @RequiresPermissions("select")
    @RequestMapping("select")
    public String select(){

        return "查询";
    }

}
