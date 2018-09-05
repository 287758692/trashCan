package com.trashCan.service;

import java.io.Serializable;
import java.util.Map;

import com.trashCan.model.SysLog;

public interface SysLogService {
    
    public Serializable insertSysLog(SysLog data);
    
    public boolean updateSysLog(SysLog data);
    
    public SysLog getSysLog(Integer Id);
    
    public Map<String, Object> getList(Integer pageNumber, Integer pageSize, String status);

}