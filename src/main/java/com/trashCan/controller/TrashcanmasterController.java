package com.trashCan.controller;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashCan.annotation.PrivilegeInfo;
import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.SysUser;
import com.trashCan.model.Trashcanmaster;
import com.trashCan.service.SysMenuService;
import com.trashCan.service.TrashcanmasterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trashcanmasterController")
public class TrashcanmasterController {
	private final static Logger log = Logger.getLogger(TrashcanmasterController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private TrashcanmasterService trashcanmasterService;

	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 垃圾桶管理主界面
	 *
	 */
	@RequestMapping(value = "/trashCanIndex",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "24")
	public ModelAndView trashCanIndex(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		for(Map<String, Object> map : sysMenuService.getLowMenu(24)){
			mav.addObject(map.get("menuIcon").toString(),map.get("menuAction").toString());
		}
		mav.setViewName("trashCanList");
		return mav;
	}

	/**
	 * 垃圾桶地图
	 *
	 */
	@RequestMapping(value = "/trashCanMap",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "24")
	@SystemControllerLog(value = "垃圾桶地图")
	public @ResponseBody
	Object trashCanMap(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String city = request.getParameter("city");
		resultMap = trashcanmasterService.getList(city,"");
		String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
		String json = "{\"rows\":" + jsonString + "}";
		log.info("json:"+json);
		return json;
	}

	/**
	 * 垃圾桶列表
	 *
	 */
	@RequestMapping(value = "/trashCanList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "24")
	@SystemControllerLog(value = "垃圾桶列表")
	public @ResponseBody
	Object trashCanList(HttpServletRequest request) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		String code = request.getParameter("code");
		resultMap = trashcanmasterService.getPageList(pageNumber,pageSize,code);
		String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
		String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";
		log.info("json:"+json);
		return json;
	}
	/**
	 * 垃圾桶新增界面
	 *
	 */
	@RequestMapping(value = "/trashCanAdd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "25")
	@SystemControllerLog(value = "垃圾桶新增界面")
	public @ResponseBody Object trashCanAdd(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();

		mav.addObject("lat",request.getParameter("lat"));
		mav.addObject("lng",request.getParameter("lng"));
		mav.addObject("address",request.getParameter("address"));

		mav.setViewName("trashCanAdd");
		return mav;
	}
	/**
	 * 垃圾桶新增提交
	 *
	 */
	@RequestMapping(value = "/trashCanAddConfirm", method = RequestMethod.POST)
	@PrivilegeInfo(value = "25")
	@SystemControllerLog(value = "垃圾桶新增提交")
	public @ResponseBody Object trashCanAddConfirm(HttpServletRequest request, HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcanmaster trashcanmaster = new Trashcanmaster();

		trashcanmaster.setCode(request.getParameter("code"));
		trashcanmaster.setType(request.getParameter("type"));
		trashcanmaster.setLat(request.getParameter("lat"));
		trashcanmaster.setLng(request.getParameter("lng"));
		trashcanmaster.setAddress(request.getParameter("address"));
		trashcanmaster.setUseDate(request.getParameter("useDate"));
		trashcanmaster.setStatus(1);
		trashcanmaster.setCrtOptr(User.getUserId());
		trashcanmaster.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		Serializable result = trashcanmasterService.insertTrashcanmaster(trashcanmaster);

		if (result.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "新增失败");
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "新增成功");
			resultMap.put("id", result);
		}
		return resultMap;
	}
	/**
	 * 垃圾桶修改界面
	 *
	 */
	@RequestMapping(value = "/trashCanAmd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "26")
	@SystemControllerLog(value = "垃圾桶修改界面")
	public @ResponseBody Object trashCanAmd(HttpServletRequest request) throws IOException {

		ModelAndView mav = new ModelAndView();

		Trashcanmaster trashcanmaster = trashcanmasterService.getTrashcanmaster(Integer.parseInt(request.getParameter("id")));

		mav.addObject("id",trashcanmaster.getId());
		mav.addObject("code",trashcanmaster.getCode());
		mav.addObject("type",trashcanmaster.getType());
		mav.addObject("lat",trashcanmaster.getLat());
		mav.addObject("lng",trashcanmaster.getLng());
		mav.addObject("address",trashcanmaster.getAddress());
		mav.addObject("useDate",trashcanmaster.getUseDate());
		mav.addObject("pic1",trashcanmaster.getPic1());
		mav.addObject("pic2",trashcanmaster.getPic2());
		mav.addObject("pic3",trashcanmaster.getPic3());
		mav.addObject("pic4",trashcanmaster.getPic4());
		mav.addObject("pic5",trashcanmaster.getPic5());

		mav.setViewName("trashCanAmd");
		return mav;
	}
	/**
	 * 垃圾桶修改提交
	 *
	 */
	@RequestMapping(value = "/trashCanAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "26")
	@SystemControllerLog(value = "垃圾桶修改提交")
	public @ResponseBody Object trashCanAmdConfirm(HttpServletRequest request,HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcanmaster trashcanmaster = trashcanmasterService.getTrashcanmaster(Integer.parseInt(request.getParameter("id")));

		trashcanmaster.setCode(request.getParameter("code"));
		trashcanmaster.setType(request.getParameter("type"));
		trashcanmaster.setLat(request.getParameter("lat"));
		trashcanmaster.setLng(request.getParameter("lng"));
		trashcanmaster.setAddress(request.getParameter("address"));
		trashcanmaster.setUseDate(request.getParameter("useDate"));
		trashcanmaster.setStatus(1);
		trashcanmaster.setCrtOptr(User.getUserId());
		trashcanmaster.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		boolean result = trashcanmasterService.updateTrashcanmaster(trashcanmaster);

		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "修改成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "修改失败");
		}
		return resultMap;
	}
	/**
	 * 垃圾桶删除
	 *
	 */
	@RequestMapping(value = "/trashCanDel", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "27")
	@SystemControllerLog(value = "垃圾桶删除")
	public @ResponseBody Object trashCanDel(HttpServletRequest request,HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcanmaster trashcanmaster = trashcanmasterService.getTrashcanmaster(Integer.parseInt(request.getParameter("id")));

		trashcanmaster.setStatus(0);
		trashcanmaster.setModOptr(User.getUserId());
		trashcanmaster.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		boolean result = trashcanmasterService.updateTrashcanmaster(trashcanmaster);

		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "删除成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "删除失败");
		}
		return resultMap;
	}
}