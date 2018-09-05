package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_log")
public class SysLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** logId */
    @Column(name = "logId")
    private Integer logId;

    /** description */
    @Column(name = "description")
    private String description;

    /** method */
    @Column(name = "method")
    private String method;

    /** Type */
    @Column(name = "type")
    private Integer type;

    /** RequestIp */
    @Column(name = "requestIp")
    private String requestIp;

    /** ExceptionCode */
    @Column(name = "exceptionCode")
    private String exceptionCode;

    /** ExceptionDetail */
    @Column(name = "exceptionDetail")
    private String exceptionDetail;

    /** Params */
    @Column(name = "params")
    private String params;

    /** status */
    @Column(name = "status")
    private Integer status;

    /** 新增用户 */
    @Column(name = "crtOptr")
    private Integer crtOptr;

    /** 新增日期 */
    @Column(name = "crtTime")
    private String crtTime;



    /**
     * 获取 logId 的值
     * @return Integer
     */
    public Integer getLogId() {
        return logId;
    }
    
    /**
     * 设置logId 的值
     */
    public SysLog setLogId(Integer logId) {
        this.logId = logId;
        return this;
    }

    /**
     * 获取 description 的值
     * @return String
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * 设置description 的值
     */
    public SysLog setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 获取 method 的值
     * @return String
     */
    public String getMethod() {
        return method;
    }
    
    /**
     * 设置method 的值
     */
    public SysLog setMethod(String method) {
        this.method = method;
        return this;
    }

    /**
     * 获取 Type 的值
     * @return Integer
     */
    public Integer getType() {
        return type;
    }
    
    /**
     * 设置Type 的值
     */
    public SysLog setType(Integer type) {
        this.type = type;
        return this;
    }

    /**
     * 获取 RequestIp 的值
     * @return String
     */
    public String getRequestIp() {
        return requestIp;
    }
    
    /**
     * 设置RequestIp 的值
     */
    public SysLog setRequestIp(String requestIp) {
        this.requestIp = requestIp;
        return this;
    }

    /**
     * 获取 ExceptionCode 的值
     * @return String
     */
    public String getExceptionCode() {
        return exceptionCode;
    }
    
    /**
     * 设置ExceptionCode 的值
     */
    public SysLog setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
        return this;
    }

    /**
     * 获取 ExceptionDetail 的值
     * @return String
     */
    public String getExceptionDetail() {
        return exceptionDetail;
    }
    
    /**
     * 设置ExceptionDetail 的值
     */
    public SysLog setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
        return this;
    }

    /**
     * 获取 Params 的值
     * @return String
     */
    public String getParams() {
        return params;
    }
    
    /**
     * 设置Params 的值
     */
    public SysLog setParams(String params) {
        this.params = params;
        return this;
    }

    /**
     * 获取 status 的值
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * 设置status 的值
     */
    public SysLog setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * 获取 新增用户 的值
     * @return Integer
     */
    public Integer getCrtOptr() {
        return crtOptr;
    }
    
    /**
     * 设置新增用户 的值
     */
    public SysLog setCrtOptr(Integer crtOptr) {
        this.crtOptr = crtOptr;
        return this;
    }

    /**
     * 获取 新增日期 的值
     * @return String
     */
    public String getCrtTime() {
        return crtTime;
    }
    
    /**
     * 设置新增日期 的值
     */
    public SysLog setCrtTime(String crtTime) {
        this.crtTime = crtTime;
        return this;
    }

}