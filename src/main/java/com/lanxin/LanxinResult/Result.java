package com.lanxin.LanxinResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private  Object date;




    public static Result ok(Object date){
      Result result=new Result(200,"操作成功",date);
      return  result;
    }



}
