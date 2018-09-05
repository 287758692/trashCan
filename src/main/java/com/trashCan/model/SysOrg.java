package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_org")
public class SysOrg implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** orgId */
    @Column(name = "orgId")
    private Integer orgId;

    /** porgId */
    @Column(name = "porgId")
    private Integer porgId;

    /** orgCode */
    @Column(name = "orgCode")
    private String orgCode;

    /** orgName */
    @Column(name = "orgName")
    private String orgName;

    /** orgDesc */
    @Column(name = "orgDesc")
    private String orgDesc;

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
     * 获取 orgId 的值
     * @return Integer
     */
    public Integer getOrgId() {
        return orgId;
    }
    
    /**
     * 设置orgId 的值
     */
    public SysOrg setOrgId(Integer orgId) {
        this.orgId = orgId;
        return this;
    }

    /**
     * 获取 porgId 的值
     * @return Integer
     */
    public Integer getPorgId() {
        return porgId;
    }
    
    /**
     * 设置porgId 的值
     */
    public SysOrg setPorgId(Integer porgId) {
        this.porgId = porgId;
        return this;
    }

    /**
     * 获取 orgCode 的值
     * @return String
     */
    public String getOrgCode() {
        return orgCode;
    }
    
    /**
     * 设置orgCode 的值
     */
    public SysOrg setOrgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    /**
     * 获取 orgName 的值
     * @return String
     */
    public String getOrgName() {
        return orgName;
    }
    
    /**
     * 设置orgName 的值
     */
    public SysOrg setOrgName(String orgName) {
        this.orgName = orgName;
        return this;
    }

    /**
     * 获取 orgDesc 的值
     * @return String
     */
    public String getOrgDesc() {
        return orgDesc;
    }
    
    /**
     * 设置orgDesc 的值
     */
    public SysOrg setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
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
    public SysOrg setStatus(Integer status) {
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
    public SysOrg setCrtOptr(Integer crtOptr) {
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
    public SysOrg setCrtTime(String crtTime) {
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
     */
    public SysOrg setModOptr(Integer modOptr) {
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
     */
    public SysOrg setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }
}