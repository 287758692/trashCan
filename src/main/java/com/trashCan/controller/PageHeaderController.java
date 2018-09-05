package com.trashCan.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.trashCan.annotation.SystemControllerLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trashCan.model.SysUser;
import com.trashCan.service.SysUserService;

@Controller
@RequestMapping("/pageHeader")
public class PageHeaderController {
	private final static Logger log = Logger.getLogger(PageHeaderController.class);
	
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 更新用户信息
	 * 
	 */
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	@SystemControllerLog(value = "更新用户信息")
	public @ResponseBody Object updateUser(HttpServletRequest request,HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		user.setUserCode(request.getParameter("HuserCode"));
		user.setUserName(request.getParameter("HuserName"));
		user.setMobileNo(request.getParameter("HmobileNo"));
		user.setAddress(request.getParameter("Haddress"));
		user.setEMail(request.getParameter("HeMail"));
		user.setModOptr(Integer.parseInt(request.getParameter("userId")));
		user.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysUserService.updateSysUser(user);
		
		if (result) {
			session.setAttribute("User", user);
			map.put("isSuc", true);
		} else {
			map.put("isSuc", false);
		}
		
		return map;
		
	}

	/**
	 * 检查用户密码
	 * 
	 */
	@RequestMapping(value = "/checkPass", method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "检查用户密码")
	public @ResponseBody Object checkPass(HttpServletRequest request,HttpSession session){
		
		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("userId")));
		
		if (request.getParameter("passwd").equals(user.getPasswd())) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 更新用户密码
	 * 
	 */
	@RequestMapping(value = "/updatePass", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "更新用户密码")
	public @ResponseBody Object updatePass(HttpServletRequest request,HttpSession session){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		SysUser user = sysUserService.getSysUser(Integer.parseInt(request.getParameter("puserId")));
		
		user.setPasswd(request.getParameter("NewPassword"));
		user.setModOptr(Integer.parseInt(request.getParameter("puserId")));
		user.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		boolean result = sysUserService.updateSysUser(user);
		if(result){
			session.setAttribute("User", user);
		}
		map.put("isSuc", result);
		
		return map;
		
	}
}