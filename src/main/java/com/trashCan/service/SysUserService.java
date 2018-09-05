package com.trashCan.service;

import java.io.Serializable;
import java.util.Map;

import com.trashCan.model.SysUser;

public interface SysUserService {
    
    public Serializable insertSysUser(SysUser data);
    
    public boolean updateSysUser(SysUser data);
    
    public SysUser getSysUser(Integer Id);
    
    public SysUser getUsers(String loginName, String passWord);
    
    public Map<String, Object> getList(Integer pageNumber, Integer pageSize, String userName);

}