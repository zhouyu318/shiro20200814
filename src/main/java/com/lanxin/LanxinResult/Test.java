package com.lanxin.LanxinResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Test  extends  RuntimeException{
    private  Integer code;
    private String msg;




}
