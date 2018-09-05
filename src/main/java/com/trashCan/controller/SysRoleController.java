package com.trashCan.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashCan.annotation.SystemControllerLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trashCan.annotation.PrivilegeInfo;
import com.trashCan.model.SysRole;
import com.trashCan.model.SysRoleMenu;
import com.trashCan.model.SysUser;
import com.trashCan.service.SysMenuService;
import com.trashCan.service.SysRoleMenuService;
import com.trashCan.service.SysRoleService;

@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController {
	private final static Logger log = Logger.getLogger(SysRoleController.class);
    private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 角色管理主界面
	 * 
	 */
	@RequestMapping(value = "/roleIndex",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "7")
	public ModelAndView roleIndex(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		for(Map<String, Object> map : sysMenuService.getLowMenu(7)){
			mav.addObject(map.get("menuIcon").toString(),map.get("menuAction").toString());
		}
		mav.setViewName("roleList");
		return mav;
	}
	
	/**
	 * 角色列表
	 * 
	 */
	@RequestMapping(value = "/roleList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "7")
	@SystemControllerLog(value = "角色列表")
	public @ResponseBody Object roleList(HttpServletRequest request) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>();  
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
        String roleName = request.getParameter("roleName"); 
        
        resultMap = sysRoleService.getList(pageNumber, pageSize,roleName);
        
        String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
        String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
        
        log.info("json:"+json);
        return json;
	}
	/**
	 * 角色新增界面
	 * 
	 */
	@RequestMapping(value = "/roleAdd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "8")
	@SystemControllerLog(value = "角色新增界面")
	public @ResponseBody Object roleAdd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("roleAdd");
		return mav;
	}
	/**
	 * 角色新增提交
	 * 
	 */
	@RequestMapping(value = "/roleAddConfirm", method = RequestMethod.POST)
	@PrivilegeInfo(value = "8")
	@SystemControllerLog(value = "角色新增提交")
	public @ResponseBody Object roleAddConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysRole role = new SysRole();
		
		role.setRoleName(request.getParameter("roleName"));
		role.setRoleDesc(request.getParameter("roleDesc"));
		role.setStatus(1);
		role.setCrtOptr(User.getUserId());
		role.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		Serializable result1 = sysRoleService.insertSysRole(role);
		
		if (result1.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "新增角色失败");
		} else {
			List<String> roleTree = new ArrayList<String>();
			roleTree = Arrays.asList(request.getParameter("roleTree").toString().split(","));
			for (String menuId : roleTree) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				
				roleMenu.setRoleId(Integer.parseInt(result1.toString()));
				roleMenu.setMenuId(Integer.parseInt(menuId));
				roleMenu.setStatus(1);
				roleMenu.setCrtOptr(User.getUserId());
				roleMenu.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				Serializable result2 = sysRoleMenuService.insertSysRoleMenu(roleMenu);
				
				if (result2.equals(0)) {
					resultMap.put("isSuc", false);
					resultMap.put("errMsg", "新增角色失败");
					break;
				}else{
					resultMap.put("isSuc", true);
					resultMap.put("errMsg", "新增角色成功");
				}
			}
		}
		return resultMap;
	}
	/**
	 * 角色修改界面
	 * 
	 */
	@RequestMapping(value = "/roleAmd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "9")
	@SystemControllerLog(value = "角色修改界面")
	public @ResponseBody Object roleAmd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		
		SysRole role = sysRoleService.getSysRole(Integer.parseInt(request.getParameter("roleId")));
		
		mav.addObject("roleId",role.getRoleId());
		mav.addObject("roleName",role.getRoleName());
		mav.addObject("roleDesc",role.getRoleDesc());
		
		mav.setViewName("roleAmd");
		return mav;
	}
	/**
	 * 角色修改提交
	 * 
	 */
	@RequestMapping(value = "/roleAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "9")
	@SystemControllerLog(value = "角色修改提交")
	public @ResponseBody Object roleAmdConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysRole role = sysRoleService.getSysRole(Integer.parseInt(request.getParameter("roleId")));
		
		role.setRoleName(request.getParameter("roleName"));
		role.setRoleDesc(request.getParameter("roleDesc"));
		role.setStatus(1);
		role.setModOptr(User.getUserId());
		role.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result1 = sysRoleService.updateSysRole(role);
		
		if (result1) {
			sysRoleMenuService.deleteSysRoleMenu(Integer.parseInt(request.getParameter("roleId")));
			List<String> roleTree = new ArrayList<String>();
			roleTree = Arrays.asList(request.getParameter("roleTree").toString().split(","));
			for (String menuId : roleTree) {
				SysRoleMenu roleMenu = new SysRoleMenu();
				
				roleMenu.setRoleId(Integer.parseInt(request.getParameter("roleId")));
				roleMenu.setMenuId(Integer.parseInt(menuId));
				roleMenu.setStatus(1);
				roleMenu.setCrtOptr(User.getUserId());
				roleMenu.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				Serializable result2 = sysRoleMenuService.insertSysRoleMenu(roleMenu);
				
				if (result2.equals(0)) {
					resultMap.put("isSuc", false);
					resultMap.put("errMsg", "修改角色失败");
					break;
				}else{
					resultMap.put("isSuc", true);
					resultMap.put("errMsg", "修改角色成功");
				}
			}
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "修改角色失败");
		}
		
		return resultMap;
	}
	/**
	 * 角色删除
	 * 
	 */
	@RequestMapping(value = "/roleDel", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "10")
	@SystemControllerLog(value = "角色删除")
	public @ResponseBody Object roleDel(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysRole role = sysRoleService.getSysRole(Integer.parseInt(request.getParameter("roleId")));
		
		role.setStatus(0);
		role.setModOptr(User.getUserId());
		role.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result1 = sysRoleService.updateSysRole(role);
		
		if (result1) {
			for(Map<String, Object> map : sysRoleMenuService.rolePrivilege(Integer.parseInt(request.getParameter("roleId")))){
				SysRoleMenu menuRole = sysRoleMenuService.getSysRoleMenu(Integer.parseInt(map.get("roleMenuId").toString()));
				menuRole.setStatus(0);
				menuRole.setModOptr(User.getUserId());
				menuRole.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				Serializable result2 = sysRoleMenuService.insertSysRoleMenu(menuRole);
				if (result2.equals(0)) {
					resultMap.put("isSuc", false);
					resultMap.put("errMsg", "删除角色失败");
					break;
				}else{
					resultMap.put("isSuc", true);
					resultMap.put("errMsg", "删除角色成功");
				}
			}
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "删除角色失败");
		}
		
		return resultMap;
	}
	
	@RequestMapping(value = "/menuAll",produces="application/json;charset=UTF-8")
	public @ResponseBody Object menuAll(HttpServletRequest request) {
		List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();
		menus = sysMenuService.getMenuAll(Integer.parseInt(request.getParameter("roleId")));
		return menus;
	}

	/**
	 * 选择角色
	 *
	 */
	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public @ResponseBody Object product(HttpServletRequest request,HttpSession session) {
		return sysRoleService.getComboList();
	}
}