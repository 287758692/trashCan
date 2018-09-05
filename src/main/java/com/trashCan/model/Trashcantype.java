package com.trashCan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trashcantype")
public class Trashcantype implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
    /** id */
    @Column(name = "id")
    private Integer id;

    /** 类型 */
    @Column(name = "name")
    private String name;

    /** 尺寸 */
    @Column(name = "size")
    private String size;

    /** 材质 */
    @Column(name = "material")
    private String material;

    /** 电源 */
    @Column(name = "power")
    private String power;

    /** 寿命 */
    @Column(name = "lifetime")
    private String lifetime;

    /** 灯光 */
    @Column(name = "light")
    private String light;

    /** 颜色 */
    @Column(name = "color")
    private String color;

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
     * @param Integer id
     */
    public Trashcantype setId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 获取 类型 的值
     * @return String
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置类型 的值
     * @param String name
     */
    public Trashcantype setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取 尺寸 的值
     * @return String
     */
    public String getSize() {
        return size;
    }
    
    /**
     * 设置尺寸 的值
     * @param String size
     */
    public Trashcantype setSize(String size) {
        this.size = size;
        return this;
    }

    /**
     * 获取 材质 的值
     * @return String
     */
    public String getMaterial() {
        return material;
    }
    
    /**
     * 设置材质 的值
     * @param String material
     */
    public Trashcantype setMaterial(String material) {
        this.material = material;
        return this;
    }

    /**
     * 获取 电源 的值
     * @return String
     */
    public String getPower() {
        return power;
    }
    
    /**
     * 设置电源 的值
     * @param String power
     */
    public Trashcantype setPower(String power) {
        this.power = power;
        return this;
    }

    /**
     * 获取 寿命 的值
     * @return String
     */
    public String getLifetime() {
        return lifetime;
    }
    
    /**
     * 设置寿命 的值
     * @param String lifetime
     */
    public Trashcantype setLifetime(String lifetime) {
        this.lifetime = lifetime;
        return this;
    }

    /**
     * 获取 灯光 的值
     * @return String
     */
    public String getLight() {
        return light;
    }
    
    /**
     * 设置灯光 的值
     * @param String light
     */
    public Trashcantype setLight(String light) {
        this.light = light;
        return this;
    }

    /**
     * 获取 颜色 的值
     * @return String
     */
    public String getColor() {
        return color;
    }
    
    /**
     * 设置颜色 的值
     * @param String color
     */
    public Trashcantype setColor(String color) {
        this.color = color;
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
     * @param Integer status
     */
    public Trashcantype setStatus(Integer status) {
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
    public Trashcantype setCrtOptr(Integer crtOptr) {
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
    public Trashcantype setCrtTime(String crtTime) {
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
    public Trashcantype setModOptr(Integer modOptr) {
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
    public Trashcantype setModTime(String modTime) {
        this.modTime = modTime;
        return this;
    }


    /**
     * 设置主表 的值
     * @param main data
     */
    public Trashcantype setMain(Trashcantype data) {
        this.id = data.getId();
        this.name = data.getName();
        this.size = data.getSize();
        this.material = data.getMaterial();
        this.power = data.getPower();
        this.lifetime = data.getLifetime();
        this.light = data.getLight();
        this.color = data.getColor();
        this.status = data.getStatus();
        this.crtOptr = data.getCrtOptr();
        this.crtTime = data.getCrtTime();
        this.modOptr = data.getModOptr();
        this.modTime = data.getModTime();

    	return this;
    }

}