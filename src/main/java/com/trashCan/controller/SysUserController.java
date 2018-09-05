package com.trashCan.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trashCan.annotation.PrivilegeInfo;
import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.SysUser;
import com.trashCan.service.SysMenuService;
import com.trashCan.service.SysUserService;
import com.trashCan.utils.EncryptUtil;

@Controller
@RequestMapping("/sysUserController")
public class SysUserController {
	private final static Logger log = Logger.getLogger(SysUserController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 用户管理主界面
	 * 
	 */
	@RequestMapping(value = "/userIndex",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "2")
	public ModelAndView userIndex(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		for(Map<String, Object> map : sysMenuService.getLowMenu(2)){
			mav.addObject(map.get("menuIcon").toString(),map.get("menuAction").toString());
		}
		mav.setViewName("userList");
		return mav;
	}
	
	/**
	 * 用户列表
	 * 
	 */
	@RequestMapping(value = "/userList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "2")
	@SystemControllerLog(value = "用户列表")
	public @ResponseBody Object userList(HttpServletRequest request) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>();  
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
        String userName = request.getParameter("userName"); 
        resultMap = sysUserService.getList(pageNumber, pageSize, userName);
        
        String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
        String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
        
        log.info("json:"+json);
        return json;
	}
	/**
	 * 用户新增界面
	 * 
	 */
	@RequestMapping(value = "/userAdd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "3")
	@SystemControllerLog(value = "用户新增界面")
	public @ResponseBody Object userAdd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("userAdd");
		return mav;
	}
	/**
	 * 用户新增提交
	 * 
	 */
	@RequestMapping(value = "/userAddConfirm", method = RequestMethod.POST)
	@PrivilegeInfo(value = "3")
	@SystemControllerLog(value = "用户新增提交")
	public @ResponseBody Object userAddConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysUser user = new SysUser();
		
		user.setLoginName(request.getParameter("loginName"));
		try {
			user.setPasswd(EncryptUtil.addMD5("123456").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setUserName(request.getParameter("userName"));
		user.setSex(request.getParameter("sex"));
		user.setUserCode(request.getParameter("userCode"));
		user.setMobileNo(request.getParameter("mobileNo"));
		user.setEMail(request.getParameter("eMail"));
		user.setAddress(request.getParameter("address"));
		user.setRoleId(Integer.parseInt(request.getParameter("roleId")));
		user.setStatus(1);
		user.setCrtOptr(User.getUserId());
		user.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		Serializable result = sysUserService.insertSysUser(user);
		
		if (result.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "新增用户失败");
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "新增用户成功");
		}
		return resultMap;
	}
	/**
	 * 用户修改界面
	 * 
	 */
	@RequestMapping(value = "/userAmd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "4")
	@SystemControllerLog(value = "用户修改界面")
	public @ResponseBody Object userAmd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		
		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		mav.addObject("userId",user.getUserId());
		mav.addObject("loginName",user.getLoginName());
		mav.addObject("userName",user.getUserName());
		mav.addObject("sex",user.getSex());
		mav.addObject("userCode",user.getUserCode());
		mav.addObject("mobileNo",user.getMobileNo());
		mav.addObject("eMail",user.getEMail());
		mav.addObject("address",user.getAddress());
		mav.addObject("roleId",user.getRoleId());
		
		mav.setViewName("userAmd");
		return mav;
	}
	/**
	 * 用户修改提交
	 * 
	 */
	@RequestMapping(value = "/userAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "4")
	@SystemControllerLog(value = "用户修改提交")
	public @ResponseBody Object userAmdConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		user.setLoginName(request.getParameter("loginName"));
		user.setUserName(request.getParameter("userName"));
		user.setSex(request.getParameter("sex"));
		user.setUserCode(request.getParameter("userCode"));
		user.setMobileNo(request.getParameter("mobileNo"));
		user.setEMail(request.getParameter("eMail"));
		user.setAddress(request.getParameter("address"));
		user.setRoleId(Integer.parseInt(request.getParameter("roleId")));
		user.setStatus(1);
		user.setModOptr(User.getUserId());
		user.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysUserService.updateSysUser(user);
		
		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "修改用户成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "修改用户失败");
		}
		return resultMap;
	}
	/**
	 * 用户删除
	 * 
	 */
	@RequestMapping(value = "/userDel", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "5")
	@SystemControllerLog(value = "用户删除")
	public @ResponseBody Object userDel(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		user.setStatus(0);
		user.setModOptr(User.getUserId());
		user.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysUserService.updateSysUser(user);
		
		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "删除用户成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "删除用户失败");
		}
		return resultMap;
	}
	/**
	 * 初始化用户密码
	 * 
	 */
	@RequestMapping(value = "/userPas", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "5")
	@SystemControllerLog(value = "初始化用户密码")
	public @ResponseBody Object userPas(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		try {
			user.setPasswd(EncryptUtil.addMD5("123456").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setModOptr(User.getUserId());
		user.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysUserService.updateSysUser(user);
		
		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "重置密码成功,初始密码[123456]");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "重置密码失败");
		}
		return resultMap;
	}
}