package com.trashCan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.SysUser;
import com.trashCan.model.Trashcanmaster;
import com.trashCan.service.TrashcanmasterService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/WxTrashCanController")
public class WxTrashCanController {
	private final static Logger log = Logger.getLogger(WxTrashCanController.class);
	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private TrashcanmasterService trashcanmasterService;

	/**
	 * 微信登陆成功-跳转地图
	 *
	 */
	@RequestMapping(value = "/wxIndex",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "微信登陆主界面")
	public ModelAndView wxIndex(HttpServletRequest request,HttpSession session){

		ModelAndView mav = new ModelAndView();
		mav.setViewName("wxTrashCanList");
		return mav;
	}

	/**
	 * wx垃圾桶列表
	 *
	 */
	@RequestMapping(value = "/trashCanList",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "wx垃圾桶列表")
	public @ResponseBody
	Object trashCanList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String lat = request.getParameter("lat");
		String Lng = request.getParameter("Lng");
		resultMap = trashcanmasterService.getList(lat,Lng,"");
		String jsonString = objectMapper.writeValueAsString(resultMap.get("rows"));
		String json = "{\"rows\":" + jsonString + "}";
		log.info("json:"+json);
		response.addHeader("Access-Control-Allow-Origin", "*");
		return json;
	}
	/**
	 * 垃圾桶新增界面
	 *
	 */
	@RequestMapping(value = "/trashCanAdd",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "wx垃圾桶新增界面")
	public @ResponseBody Object trashCanAdd(HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();

		mav.addObject("lat",request.getParameter("lat"));
		mav.addObject("lng",request.getParameter("lng"));
		mav.addObject("address",request.getParameter("address"));

		mav.setViewName("wxTrashCanAdd");
		return mav;
	}
	/**
	 * 垃圾桶新增提交
	 *
	 */
	@RequestMapping(value = "/trashCanAddConfirm", method = RequestMethod.POST)
	@SystemControllerLog(value = "wx垃圾桶新增提交")
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
		trashcanmaster.setPic1(request.getParameter("pic1"));
		trashcanmaster.setPic2(request.getParameter("pic2"));
		trashcanmaster.setPic3(request.getParameter("pic3"));
		trashcanmaster.setPic4(request.getParameter("pic4"));
		trashcanmaster.setPic5(request.getParameter("pic5"));
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
		}
		return resultMap;
	}
	/**
	 * 垃圾桶修改界面
	 *
	 */
	@RequestMapping(value = "/trashCanAmd",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "wx垃圾桶修改界面")
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

		mav.setViewName("wxTrashCanAmd");
		return mav;
	}
	/**
	 * 垃圾桶修改提交
	 *
	 */
	@RequestMapping(value = "/trashCanAmdConfirm", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "wx垃圾桶修改提交")
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
		trashcanmaster.setPic1(request.getParameter("pic1"));
		trashcanmaster.setPic2(request.getParameter("pic2"));
		trashcanmaster.setPic3(request.getParameter("pic3"));
		trashcanmaster.setPic4(request.getParameter("pic4"));
		trashcanmaster.setPic5(request.getParameter("pic5"));
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
	@SystemControllerLog(value = "wx垃圾桶删除")
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