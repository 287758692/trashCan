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
import com.trashCan.annotation.PrivilegeInfo;
import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.SysUser;
import com.trashCan.model.Trashcantype;
import com.trashCan.service.SysMenuService;
import com.trashCan.service.TrashcantypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/trashcantypeController")
public class TrashcantypeController {
	private final static Logger log = Logger.getLogger(TrashcantypeController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private TrashcantypeService trashcantypeService;

	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 类型管理主界面
	 *
	 */
	@RequestMapping(value = "/typeIndex",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "20")
	public ModelAndView typeIndex(HttpServletRequest request){

		ModelAndView mav = new ModelAndView();
		for(Map<String, Object> map : sysMenuService.getLowMenu(20)){
			mav.addObject(map.get("menuIcon").toString(),map.get("menuAction").toString());
		}
		mav.setViewName("typeList");
		return mav;
	}

	/**
	 * 类型列表
	 *
	 */
	@RequestMapping(value = "/typeList",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "20")
	@SystemControllerLog(value = "类型列表")
	public @ResponseBody
	Object typeList(HttpServletRequest request) throws IOException {

		Map<String, Object> resultMap = new HashMap<>();
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		String name = request.getParameter("name");
		resultMap = trashcantypeService.getList(pageNumber, pageSize, name);

		String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
		String json = "{\"total\":" + resultMap.get("total") + ",\"rows\":" + jsonString + "}";

		log.info("json:"+json);
		return json;
	}
	/**
	 * 类型新增界面
	 *
	 */
	@RequestMapping(value = "/typeAdd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "21")
	@SystemControllerLog(value = "类型新增界面")
	public @ResponseBody Object typeAdd(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("typeAdd");
		return mav;
	}
	/**
	 * 类型新增提交
	 *
	 */
	@RequestMapping(value = "/typeAddConfirm", method = RequestMethod.POST)
	@PrivilegeInfo(value = "21")
	@SystemControllerLog(value = "类型新增提交")
	public @ResponseBody Object typenAddConfirm(HttpServletRequest request, HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcantype trashcantype = new Trashcantype();

		trashcantype.setName(request.getParameter("name"));
		trashcantype.setSize(request.getParameter("size"));
		trashcantype.setMaterial(request.getParameter("material"));
		trashcantype.setPower(request.getParameter("power"));
		trashcantype.setLifetime(request.getParameter("lifetime"));
		trashcantype.setLight(request.getParameter("light"));
		trashcantype.setColor(request.getParameter("color"));
		trashcantype.setStatus(1);
		trashcantype.setCrtOptr(User.getUserId());
		trashcantype.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		Serializable result = trashcantypeService.insertTrashcantype(trashcantype);

		if (result.equals(0)) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "新增失败");
		} else {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "新增成功");
		}
		return resultMap;
	}
	/**
	 * 类型修改界面
	 *
	 */
	@RequestMapping(value = "/typeAmd",produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "22")
	@SystemControllerLog(value = "类型修改界面")
	public @ResponseBody Object typeAmd(HttpServletRequest request) throws IOException {

		ModelAndView mav = new ModelAndView();

		Trashcantype trashcantype = trashcantypeService.getTrashcantype(Integer.parseInt(request.getParameter("id")));

		mav.addObject("id",trashcantype.getId());
		mav.addObject("name",trashcantype.getName());
		mav.addObject("size",trashcantype.getSize());
		mav.addObject("material",trashcantype.getMaterial());
		mav.addObject("power",trashcantype.getPower());
		mav.addObject("lifetime",trashcantype.getLifetime());
		mav.addObject("light",trashcantype.getLight());
		mav.addObject("color",trashcantype.getColor());

		mav.setViewName("typeAmd");
		return mav;
	}
	/**
	 * 类型修改提交
	 *
	 */
	@RequestMapping(value = "/typeAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "22")
	@SystemControllerLog(value = "类型修改提交")
	public @ResponseBody Object typeAmdConfirm(HttpServletRequest request,HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcantype trashcantype = trashcantypeService.getTrashcantype(Integer.parseInt(request.getParameter("id")));

		trashcantype.setName(request.getParameter("name"));
		trashcantype.setSize(request.getParameter("size"));
		trashcantype.setMaterial(request.getParameter("material"));
		trashcantype.setPower(request.getParameter("power"));
		trashcantype.setLifetime(request.getParameter("lifetime"));
		trashcantype.setLight(request.getParameter("light"));
		trashcantype.setColor(request.getParameter("color"));
		trashcantype.setStatus(1);
		trashcantype.setCrtOptr(User.getUserId());
		trashcantype.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		boolean result = trashcantypeService.updateTrashcantype(trashcantype);

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
	 * 类型删除
	 *
	 */
	@RequestMapping(value = "/typeDel", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@PrivilegeInfo(value = "23")
	@SystemControllerLog(value = "类型删除")
	public @ResponseBody Object typeDel(HttpServletRequest request,HttpSession session) throws IOException {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		SysUser User = (SysUser) session.getAttribute("User");

		Trashcantype trashcantype = trashcantypeService.getTrashcantype(Integer.parseInt(request.getParameter("id")));

		trashcantype.setStatus(0);
		trashcantype.setModOptr(User.getUserId());
		trashcantype.setModTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		boolean result = trashcantypeService.updateTrashcantype(trashcantype);

		if (result) {
			resultMap.put("isSuc", true);
			resultMap.put("errMsg", "删除成功");
		} else {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "删除失败");
		}
		return resultMap;
	}

	/**
	 * 选择类型
	 *
	 */
	@RequestMapping(value = "/type", method = RequestMethod.POST)
	@SystemControllerLog(value = "选择类型")
	public @ResponseBody Object product(HttpServletRequest request,HttpSession session) {
		return trashcantypeService.getComboList();
	}

	/**
	 * 获取类型信息
	 *
	 */
	@RequestMapping(value = "/getTypeInfo", method = RequestMethod.POST)
	@SystemControllerLog(value = "获取类型信息")
	public @ResponseBody Object type(HttpServletRequest request,HttpSession session) {
		return trashcantypeService.getTrashcantype(Integer.parseInt(request.getParameter("id")));
	}
}