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
import com.trashCan.annotation.SystemControllerLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trashCan.annotation.PrivilegeInfo;
import com.trashCan.model.SysOrg;
import com.trashCan.model.SysUser;
import com.trashCan.model.SysUserOrg;
import com.trashCan.service.SysMenuService;
import com.trashCan.service.SysOrgService;
import com.trashCan.service.SysUserOrgService;

@Controller
@RequestMapping("/sysOrgController")
public class SysOrgController {
	private final static Logger log = Logger.getLogger(SysOrgController.class);
	private ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private SysOrgService sysOrgService;
	
	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private SysUserOrgService sysUserOrgService;
	
	/**
	 * 机构管理主界面
	 * 
	 */
	@RequestMapping(value = "/orgIndex",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "11")
	public ModelAndView orgIndex(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView();
		for(Map<String, Object> map : sysMenuService.getLowMenu(11)){
			mav.addObject(map.get("menuIcon").toString(),map.get("menuAction").toString());
		}
		mav.setViewName("orgList");
		return mav;
	}
	
	/**
	 * 机构列表
	 * 
	 */
	@RequestMapping(value = "/orgList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "11")
	@SystemControllerLog(value = "机构列表")
	public @ResponseBody Object orgList(HttpServletRequest request) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>();  
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
        String orgName = request.getParameter("orgName"); 
        
        resultMap = sysOrgService.getList(pageNumber, pageSize,orgName);
        
        String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
        String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
        
        log.info("json:"+json);
        return json;
	}
	/**
	 * 机构新增界面
	 * 
	 */
	@RequestMapping(value = "/orgAdd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "12")
	@SystemControllerLog(value = "机构新增界面")
	public @ResponseBody Object orgAdd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("orgAdd");
		return mav;
	}
	/**
	 * 机构新增提交
	 * 
	 */
	@RequestMapping(value = "/orgAddConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "12")
	public @ResponseBody Object orgAddConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysOrg org = new SysOrg();
		
		org.setPorgId(0);
		org.setOrgCode(request.getParameter("orgCode"));
		org.setOrgName(request.getParameter("orgName"));
		org.setOrgDesc(request.getParameter("orgDesc"));
		org.setStatus(1);
		org.setCrtOptr(User.getUserId());
		org.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		Serializable result = sysOrgService.insertSysOrg(org);
		
		if (result.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "新增机构失败");
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "新增机构成功");
		}
		return resultMap;
	}
	/**
	 * 机构修改界面
	 * 
	 */
	@RequestMapping(value = "/orgAmd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "13")
	@SystemControllerLog(value = "机构修改界面")
	public @ResponseBody Object orgAmd(HttpServletRequest request) throws IOException {
		
		ModelAndView mav = new ModelAndView();
		
		SysOrg org = sysOrgService.getSysOrg(Integer.parseInt(request.getParameter("orgId")));
		
		mav.addObject("orgId",org.getOrgId());
		mav.addObject("orgCode",org.getOrgCode());
		mav.addObject("orgName",org.getOrgName());
		mav.addObject("orgDesc",org.getOrgDesc());
		
		mav.setViewName("orgAmd");
		return mav;
	}
	/**
	 * 机构修改提交
	 * 
	 */
	@RequestMapping(value = "/orgAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "13")
	@SystemControllerLog(value = "机构修改提交")
	public @ResponseBody Object orgAmdConfirm(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysOrg org = sysOrgService.getSysOrg(Integer.parseInt(request.getParameter("orgId")));
		
		org.setOrgCode(request.getParameter("orgCode"));
		org.setOrgName(request.getParameter("orgName"));
		org.setOrgDesc(request.getParameter("orgDesc"));
		org.setStatus(1);
		org.setModOptr(User.getUserId());
		org.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysOrgService.updateSysOrg(org);
		
		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "修改机构成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "修改机构失败");
		}
		return resultMap;
	}
	/**
	 * 机构删除
	 * 
	 */
	@RequestMapping(value = "/orgDel", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "14")
	@SystemControllerLog(value = "机构删除")
	public @ResponseBody Object orgDel(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");

		SysOrg org = sysOrgService.getSysOrg(Integer.parseInt(request.getParameter("orgId")));
		
		org.setStatus(0);
		org.setModOptr(User.getUserId());
		org.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysOrgService.updateSysOrg(org);
		
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
	 * 机构成员查询
	 * 
	 */
	@RequestMapping(value = "/userSearch",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "15")
	@SystemControllerLog(value = "机构成员查询")
	public @ResponseBody Object orgUserSearch(HttpServletRequest request) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
        Integer orgId = Integer.parseInt(request.getParameter("orgId")); 
        
        resultMap = sysOrgService.getOrgUserList(pageNumber, pageSize,orgId);
        
        String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
        String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
        
        log.info("json:"+json);
        return json;
	}
	/**
	 * 机构成员添加-列表
	 * 
	 */
	@RequestMapping(value = "/orgUserInList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "16")
	@SystemControllerLog(value = "机构成员添加-列表")
	public @ResponseBody Object orgUserInList(HttpServletRequest request) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));  
        int pageNumber = Integer.parseInt(request.getParameter("pageNumber")); 
        
        resultMap = sysOrgService.getOrgUserInList(pageNumber, pageSize);
        
        String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
        String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
        
        log.info("json:"+json);
        return json;
	}
	/**
	 * 机构成员添加-提交
	 * 
	 */
	@RequestMapping(value = "/userIn", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "16")
	@SystemControllerLog(value = "机构成员添加-提交")
	public @ResponseBody Object userIn(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");
		
		SysUserOrg userOrg = new SysUserOrg();
		
		userOrg.setUserId(Integer.parseInt(request.getParameter("userId")));
		userOrg.setOrgId(Integer.parseInt(request.getParameter("orgId")));
		userOrg.setStatus(1);
		userOrg.setCrtOptr(User.getUserId());
		userOrg.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		Serializable result = sysUserOrgService.insertSysUserOrg(userOrg);
		
		if (result.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "成员添加失败");
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "成员添加成功");
		}
		return resultMap;
	}
	/**
	 * 机构成员删除
	 * 
	 */
	@RequestMapping(value = "/userOut", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "17")
	@SystemControllerLog(value = "机构成员删除")
	public @ResponseBody Object userOut(HttpServletRequest request,HttpSession session) throws IOException {
		
		Map<String, Object> resultMap = new HashMap<>(); 
		
		SysUser User = (SysUser) session.getAttribute("User");
		
		SysUserOrg userOrg = sysUserOrgService.getSysUserOrg(Integer.parseInt(request.getParameter("userOrgId")));
		
		userOrg.setStatus(0);
		userOrg.setCrtOptr(User.getUserId());
		userOrg.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		
		boolean result = sysUserOrgService.updateSysUserOrg(userOrg);
		
		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "成员删除成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "成员删除失败");
		}
		return resultMap;
	}
}