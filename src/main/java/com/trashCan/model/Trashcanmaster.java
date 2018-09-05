package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trashcanmaster")
public class Trashcanmaster implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** id */
    @Column(name = "id")
    private Integer id;

    /** 编号 */
    @Column(name = "code")
    private String code;

    /** 类型 */
    @Column(name = "type")
    private String type;

    /** 坐标Lat */
    @Column(name = "lat")
    private String lat;

    /** 坐标Lng */
    @Column(name = "lng")
    private String lng;

    /** 地址 */
    @Column(name = "address")
    private String address;

    /** 使用日期 */
    @Column(name = "useDate")
    private String useDate;

    /** 图片1 */
    @Column(name = "pic1")
    private String pic1;

    /** 图片2 */
    @Column(name = "pic2")
    private String pic2;

    /** 图片3 */
    @Column(name = "pic3")
    private String pic3;

    /** 图片4 */
    @Column(name = "pic4")
    private String pic4;

    /** 图片5 */
    @Column(name = "pic5")
    private String pic5;

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
     * 获取 id 的值
     * @return Integer
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * 设置id 的值
     * @param /Integer id
     */
    public Trashcanmaster setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 编号 的值
     * @return String
     */
    public String getCode() {
        return code;
    }
    
    /**
     * 设置编号 的值
     * @param /String code
     */
    public Trashcanmaster setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * 获取 名称 的值
     * @return String
     */
    public String getType() {
        return type;
    }
    
    /**
     * 设置名称 的值
     * @param /String type
     */
    public Trashcanmaster setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * 获取 坐标Lat 的值
     * @return String
     */
    public String getLat() {
        return lat;
    }
    
    /**
     * 设置坐标Lat 的值
     * @param /String lat
     */
    public Trashcanmaster setLat(String lat) {
        this.lat = lat;
        return this;
    }

    /**
     * 获取 坐标Lng 的值
     * @return String
     */
    public String getLng() {
        return lng;
    }
    
    /**
     * 设置坐标Lng 的值
     * @param /String lng
     */
    public Trashcanmaster setLng(String lng) {
        this.lng = lng;
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
     * @param /String address
     */
    public Trashcanmaster setAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * 获取 使用日期 的值
     * @return String
     */
    public String getUseDate() {
        return useDate;
    }
    
    /**
     * 设置使用日期 的值
     * @param /String useDate
     */
    public Trashcanmaster setUseDate(String useDate) {
        this.useDate = useDate;
        return this;
    }

    /**
     * 获取 图片1 的值
     * @return String
     */
    public String getPic1() {
        return pic1;
    }
    
    /**
     * 设置图片1 的值
     * @param /String pic1
     */
    public Trashcanmaster setPic1(String pic1) {
        this.pic1 = pic1;
        return this;
    }

    /**
     * 获取 图片2 的值
     * @return String
     */
    public String getPic2() {
        return pic2;
    }
    
    /**
     * 设置图片2 的值
     * @param /String pic2
     */
    public Trashcanmaster setPic2(String pic2) {
        this.pic2 = pic2;
        return this;
    }

    /**
     * 获取 图片3 的值
     * @return String
     */
    public String getPic3() {
        return pic3;
    }
    
    /**
     * 设置图片3 的值
     * @param /String pic3
     */
    public Trashcanmaster setPic3(String pic3) {
        this.pic3 = pic3;
        return this;
    }

    /**
     * 获取 图片4 的值
     * @return String
     */
    public String getPic4() {
        return pic4;
    }
    
    /**
     * 设置图片4 的值
     * @param /String pic4
     */
    public Trashcanmaster setPic4(String pic4) {
        this.pic4 = pic4;
        return this;
    }

    /**
     * 获取 图片5 的值
     * @return String
     */
    public String getPic5() {
        return pic5;
    }
    
    /**
     * 设置图片5 的值
     * @param /String pic5
     */
    public Trashcanmaster setPic5(String pic5) {
        this.pic5 = pic5;
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
    public Trashcanmaster setStatus(Integer status) {
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
    public Trashcanmaster setCrtOptr(Integer crtOptr) {
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
    public Trashcanmaster setCrtTime(String crtTime) {
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
    public Trashcanmaster setModOptr(Integer modOptr) {
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
    public Trashcanmaster setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }

}