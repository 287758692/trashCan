package com.trashCan.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.trashCan.model.SysMenu;

public interface SysMenuService {
    
    public Serializable insertSysMenu(SysMenu data);
    
    public boolean updateSysMenu(SysMenu data);
    
    public SysMenu getSysMenu(Integer Id);
    
    public List<Map<String, Object>> getLowMenu(Integer pId);
    
    public List<Map<String, Object>> getMenuAll(Integer roleId);
    
}