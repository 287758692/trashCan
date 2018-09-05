package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user_org")
public class SysUserOrg implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** userOrgId */
    @Column(name = "userOrgId")
    private Integer userOrgId;

    /** userId */
    @Column(name = "userId")
    private Integer userId;

    /** orgId */
    @Column(name = "orgId")
    private Integer orgId;

    /** status */
    @Column(name = "status")
    private Integer status;

    /** 新增用户 */
    @Column(name = "crtOptr")
    private Integer crtOptr;

    /** 新增日期 */
    @Column(name = "crtTime")
    private String crtTime;

    /** 修改用户 */
    @Column(name = "modOptr")
    private Integer modOptr;

    /** 修改时间 */
    @Column(name = "modTime")
    private String modTime;



    /**
     * 获取 userOrgId 的值
     * @return Integer
     */
    public Integer getUserOrgId() {
        return userOrgId;
    }
    
    /**
     * 设置userOrgId 的值
     * @param /Integer userOrgId
     */
    public SysUserOrg setUserOrgId(Integer userOrgId) {
        this.userOrgId = userOrgId;
        return this;
    }

    /**
     * 获取 userId 的值
     * @return Integer
     */
    public Integer getUserId() {
        return userId;
    }
    
    /**
     * 设置userId 的值
     * @param /Integer userId
     */
    public SysUserOrg setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取 orgId 的值
     * @return Integer
     */
    public Integer getOrgId() {
        return orgId;
    }
    
    /**
     * 设置orgId 的值
     * @param /Integer orgId
     */
    public SysUserOrg setOrgId(Integer orgId) {
        this.orgId = orgId;
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
     * @param /Integer status
     */
    public SysUserOrg setStatus(Integer status) {
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
     * @param /Integer crtOptr
     */
    public SysUserOrg setCrtOptr(Integer crtOptr) {
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
     * @param /String crtTime
     */
    public SysUserOrg setCrtTime(String crtTime) {
        this.crtTime = crtTime;
        return this;
    }

    /**
     * 获取 修改用户 的值
     * @return Integer
     */
    public Integer getModOptr() {
        return modOptr;
    }
    
    /**
     * 设置修改用户 的值
     * @param /Integer modOptr
     */
    public SysUserOrg setModOptr(Integer modOptr) {
        this.modOptr = modOptr;
        return this;
    }

    /**
     * 获取 修改时间 的值
     * @return String
     */
    public String getModTime() {
        return modTime;
    }
    
    /**
     * 设置修改时间 的值
     * @param /String modTime
     */
    public SysUserOrg setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }

}