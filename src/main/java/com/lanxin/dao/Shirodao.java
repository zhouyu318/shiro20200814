package com.lanxin.dao;

import java.util.Set;

public interface Shirodao {
    public String passwordbyname(String username); //根据名称查找密码
    public Set<String> permissbyname(String username);
}
