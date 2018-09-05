package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_menu")
public class SysMenu implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** menuId */
    @Column(name = "menuId")
    private Integer menuId;

    /** pmenuId */
    @Column(name = "pmenuId")
    private Integer pmenuId;

    /** menuName */
    @Column(name = "menuName")
    private String menuName;

    /** menuDesc */
    @Column(name = "menuDesc")
    private String menuDesc;

    /** menuIcon */
    @Column(name = "menuIcon")
    private String menuIcon;

    /** menuAction */
    @Column(name = "menuAction")
    private String menuAction;

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
     * 获取 menuId 的值
     * @return Integer
     */
    public Integer getMenuId() {
        return menuId;
    }
    
    /**
     * 设置menuId 的值
     */
    public SysMenu setMenuId(Integer menuId) {
        this.menuId = menuId;
        return this;
    }

    /**
     * 获取 pmenuId 的值
     * @return Integer
     */
    public Integer getPmenuId() {
        return pmenuId;
    }
    
    /**
     * 设置pmenuId 的值
     */
    public SysMenu setPmenuId(Integer pmenuId) {
        this.pmenuId = pmenuId;
        return this;
    }

    /**
     * 获取 menuName 的值
     * @return String
     */
    public String getMenuName() {
        return menuName;
    }
    
    /**
     * 设置menuName 的值
     */
    public SysMenu setMenuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    /**
     * 获取 menuDesc 的值
     * @return String
     */
    public String getMenuDesc() {
        return menuDesc;
    }
    
    /**
     * 设置menuDesc 的值
     */
    public SysMenu setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc;
        return this;
    }

    /**
     * 获取 menuIcon 的值
     * @return String
     */
    public String getMenuIcon() {
        return menuIcon;
    }
    
    /**
     * 设置menuIcon 的值
     */
    public SysMenu setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
        return this;
    }

    /**
     * 获取 menuAction 的值
     * @return String
     */
    public String getMenuAction() {
        return menuAction;
    }
    
    /**
     * 设置menuAction 的值
     */
    public SysMenu setMenuAction(String menuAction) {
        this.menuAction = menuAction;
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
    public SysMenu setStatus(Integer status) {
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
    public SysMenu setCrtOptr(Integer crtOptr) {
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
    public SysMenu setCrtTime(String crtTime) {
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
    public SysMenu setModOptr(Integer modOptr) {
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
    public SysMenu setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }
}