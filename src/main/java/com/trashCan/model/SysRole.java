package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class SysRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** 角色ID */
    @Column(name = "roleId")
    private Integer roleId;

    /** 角色名称 */
    @Column(name = "roleName")
    private String roleName;

    /** 角色描述 */
    @Column(name = "roleDesc")
    private String roleDesc;

    /** 状态 */
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
     * 获取 角色ID 的值
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }
    
    /**
     * 设置角色ID 的值
     * @param /Integer roleId
     */
    public SysRole setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * 获取 角色名称 的值
     * @return String
     */
    public String getRoleName() {
        return roleName;
    }
    
    /**
     * 设置角色名称 的值
     * @param /String roleName
     */
    public SysRole setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    /**
     * 获取 角色描述 的值
     * @return String
     */
    public String getRoleDesc() {
        return roleDesc;
    }
    
    /**
     * 设置角色描述 的值
     * @param /String roleDesc
     */
    public SysRole setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
        return this;
    }

    /**
     * 获取 状态 的值
     * @return Integer
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * 设置状态 的值
     * @param /Integer status
     */
    public SysRole setStatus(Integer status) {
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
    public SysRole setCrtOptr(Integer crtOptr) {
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
    public SysRole setCrtTime(String crtTime) {
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
    public SysRole setModOptr(Integer modOptr) {
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
    public SysRole setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }

}