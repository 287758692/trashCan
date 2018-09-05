package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_menu")
public class SysRoleMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** roleMenuId */
    @Column(name = "roleMenuId")
    private Integer roleMenuId;

    /** roleId */
    @Column(name = "roleId")
    private Integer roleId;

    /** menuId */
    @Column(name = "menuId")
    private Integer menuId;

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
     * 获取 roleMenuId 的值
     * @return Integer
     */
    public Integer getRoleMenuId() {
        return roleMenuId;
    }
    
    /**
     * 设置roleMenuId 的值
     * @param /Integer roleMenuId
     */
    public SysRoleMenu setRoleMenuId(Integer roleMenuId) {
        this.roleMenuId = roleMenuId;
        return this;
    }

    /**
     * 获取 roleId 的值
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }
    
    /**
     * 设置roleId 的值
     * @param /Integer roleId
     */
    public SysRoleMenu setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    /**
     * 获取 menuId 的值
     * @return Integer
     */
    public Integer getMenuId() {
        return menuId;
    }
    
    /**
     * 设置menuId 的值
     * @param /Integer menuId
     */
    public SysRoleMenu setMenuId(Integer menuId) {
        this.menuId = menuId;
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
    public SysRoleMenu setStatus(Integer status) {
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
    public SysRoleMenu setCrtOptr(Integer crtOptr) {
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
    public SysRoleMenu setCrtTime(String crtTime) {
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
    public SysRoleMenu setModOptr(Integer modOptr) {
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
    public SysRoleMenu setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }

}