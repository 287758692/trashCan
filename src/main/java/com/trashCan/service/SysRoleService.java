package com.trashCan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.trashCan.model.SysRole;

public interface SysRoleService {
    
    public Serializable insertSysRole(SysRole data);
    
    public boolean updateSysRole(SysRole data);
    
    public SysRole getSysRole(Integer Id);
    
    public List<Map<String, Object>> getComboList();
    
    public Map<String, Object> getList(Integer pageNumber, Integer pageSize, String roleName);

}