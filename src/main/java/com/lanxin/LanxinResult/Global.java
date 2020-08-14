package com.lanxin.LanxinResult;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice//拦截所有controller
public class Global {
    @ResponseBody//json数据
    @ExceptionHandler(value = Exception.class)//捕捉所有异常
    public Result defa(Exception e){
//     e.printStackTrace(); 输出错误的

        if(e instanceof Test){//instanceof判断异常是否是自定义异常
            Test t=(Test) e;
            return new Result(t.getCode(),t.getMsg(),false);
        }
        else if(e instanceof UnauthorizedException){
            return new Result(500,"没有权限",false);
        }
        return new Result(500,"失败",null);
    }
}
