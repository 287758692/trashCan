package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** 用户Id */
    @Column(name = "userId")
    private Integer userId;

    /** 登录名 */
    @Column(name = "loginName")
    private String loginName;

    /** 密码 */
    @Column(name = "passwd")
    private String passwd;

    /** 工号 */
    @Column(name = "userCode")
    private String userCode;

    /** 姓名 */
    @Column(name = "userName")
    private String userName;

    /** 性别 */
    @Column(name = "sex")
    private String sex;

    /** 电话 */
    @Column(name = "mobileNo")
    private String mobileNo;

    /** 邮箱 */
    @Column(name = "eMail")
    private String eMail;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 头像 */
    @Column(name = "picPath")
    private String picPath;

    /** 角色ID */
    @Column(name = "roleId")
    private Integer roleId;

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
     * 获取 用户Id 的值
     * @return Integer
     */
    public Integer getUserId() {
        return userId;
    }
    
    /**
     * 设置用户Id 的值
     * @param Integer userId
     */
    public SysUser setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 获取 登录名 的值
     * @return String
     */
    public String getLoginName() {
        return loginName;
    }
    
    /**
     * 设置登录名 的值
     * @param String loginName
     */
    public SysUser setLoginName(String loginName) {
        this.loginName = loginName;
        return this;
    }

    /**
     * 获取 密码 的值
     * @return String
     */
    public String getPasswd() {
        return passwd;
    }
    
    /**
     * 设置密码 的值
     * @param String passwd
     */
    public SysUser setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    /**
     * 获取 工号 的值
     * @return String
     */
    public String getUserCode() {
        return userCode;
    }
    
    /**
     * 设置工号 的值
     * @param String userCode
     */
    public SysUser setUserCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    /**
     * 获取 姓名 的值
     * @return String
     */
    public String getUserName() {
        return userName;
    }
    
    /**
     * 设置姓名 的值
     * @param String userName
     */
    public SysUser setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    /**
     * 获取 性别 的值
     * @return String
     */
    public String getSex() {
        return sex;
    }
    
    /**
     * 设置性别 的值
     * @param String sex
     */
    public SysUser setSex(String sex) {
        this.sex = sex;
        return this;
    }

    /**
     * 获取 电话 的值
     * @return String
     */
    public String getMobileNo() {
        return mobileNo;
    }
    
    /**
     * 设置电话 的值
     * @param String mobileNo
     */
    public SysUser setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
        return this;
    }

    /**
     * 获取 邮箱 的值
     * @return String
     */
    public String getEMail() {
        return eMail;
    }
    
    /**
     * 设置邮箱 的值
     * @param String eMail
     */
    public SysUser setEMail(String eMail) {
        this.eMail = eMail;
        return this;
    }

    /**
     * 获取 地址 的值
     * @return String
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 设置地址 的值
     * @param String address
     */
    public SysUser setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * 获取 头像 的值
     * @return String
     */
    public String getPicPath() {
        return picPath;
    }
    
    /**
     * 设置头像 的值
     * @param String picPath
     */
    public SysUser setPicPath(String picPath) {
        this.picPath = picPath;
        return this;
    }

    /**
     * 获取 角色ID 的值
     * @return Integer
     */
    public Integer getRoleId() {
        return roleId;
    }
    
    /**
     * 设置角色ID 的值
     * @param Integer roleId
     */
    public SysUser setRoleId(Integer roleId) {
        this.roleId = roleId;
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
     * @param Integer status
     */
    public SysUser setStatus(Integer status) {
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
     * @param Integer crtOptr
     */
    public SysUser setCrtOptr(Integer crtOptr) {
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
     * @param String crtTime
     */
    public SysUser setCrtTime(String crtTime) {
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
     * @param Integer modOptr
     */
    public SysUser setModOptr(Integer modOptr) {
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
     * @param String modTime
     */
    public SysUser setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }

}