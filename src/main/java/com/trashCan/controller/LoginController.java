package com.trashCan.controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.SysUser;
import com.trashCan.service.SysRoleMenuService;
import com.trashCan.service.SysUserService;
import com.trashCan.utils.DrawImageUtil;
import com.trashCan.utils.MenuVo;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final static Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	/**
	 * 验证码
	 * 
	 * @param  /null
	 * @return 返回验证码图片.jpg
	 */
	@RequestMapping("/image")
	public void loginImage(HttpServletRequest request, 
						  HttpServletResponse response,
						  HttpSession session) throws IOException {
		// 1.在内存中创建一张图片
		BufferedImage bi = new BufferedImage(DrawImageUtil.WIDTH, DrawImageUtil.HEIGHT, BufferedImage.TYPE_INT_RGB);
		// 2.得到图片
		Graphics g = bi.getGraphics();
		// 3.设置图片的背影色
		DrawImageUtil.setBackGround(g);
		// 4.设置图片的边框
//		DrawImage.setBorder(g);
		// 5.在图片上画干扰线
		DrawImageUtil.drawRandomLine(g);
		// 6.写在图片上随机数
		String random = DrawImageUtil.drawRandomNum((Graphics2D) g);// 根据客户端传递的createTypeFlag标识生成验证码图片
		// 7.将随机数存在session中
		session.setAttribute("checkcode", random);
		// 10.将图片写给浏览器
		OutputStream output=response.getOutputStream();
		ImageIO.write(bi, "jpg", output);
		output.flush();
		output.close();
	}
	
	/**
	 * 用户登陆
	 * 
	 * @param  /inputCode  /验证码
	 * @param  /loginName  /用户名
	 * @param  /passWord   /密码
	 * @return map        /返回是否登陆成功的消息
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody public Map<String, Object> loginCheck(HttpServletRequest request,HttpSession session){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String inputCode = request.getParameter("inputCode");
		String loginName = request.getParameter("loginName");
		String passWord = request.getParameter("passWord");
		
		String code = session.getAttribute("checkcode").toString();
		if (code == null) {
			resultMap.put("isSuc", false);
			resultMap.put("errMsg", "验证码错误！");
			log.info("登录失败【"+loginName+"】验证码错误！");
		} else {
			if (StringUtils.equalsIgnoreCase(code, inputCode)) {
				SysUser sysuser = sysUserService.getUsers(loginName, passWord);
				List<MenuVo> menuList = new ArrayList<MenuVo>();
				if (sysuser != null) {
					menuList = sysRoleMenuService.getMenus(sysuser.getRoleId(), 0);
					session.setAttribute("Menu", menuList);
					session.setAttribute("User", sysuser);

					if (sysuser.getStatus() != 1) {
						resultMap.put("isSuc", false);
						resultMap.put("errMsg", "用户未激活！");
						log.info("登录失败【"+loginName+"】用户未激活！");
					}else{
						resultMap.put("isSuc", true);
						resultMap.put("errMsg", "用户登录成功！");
						log.info("登录成功【"+loginName+"】");
					}
				} else {
					resultMap.put("isSuc", false);
					resultMap.put("errMsg", "用户名或密码错误！");
					log.info("登录失败【"+loginName+"】用户名或密码错误！");
				}
			} else {
				resultMap.put("isSuc", false);
				resultMap.put("errMsg", "验证码错误！");
				log.info("登录失败【"+loginName+"】验证码错误！");
			}
		}
		return resultMap;
	}
	
	/**
	 * 跳转主界面
	 * 
	 */
	@RequestMapping(value = "/welcome",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "用户登录")
	public ModelAndView welcome(HttpServletRequest request,HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}
	
	/**
	 * 保存点击菜单的ID
	 * 
	 */
	@RequestMapping(value = "/saveMenuId")
	public void saveMenuId(HttpServletRequest request,HttpSession session) throws IOException {
		
		session.setAttribute("MenuId", request.getParameter("pmenuId"));
		session.setAttribute("SubMenuId", request.getParameter("submenuId"));
	}
	
	/**
	 * 安全退出
	 * 
	 */
	@RequestMapping(value = "/close",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "安全退出")
	public ModelAndView close(HttpSession session) {
		
		session.setAttribute("User", null);
		session.setAttribute("Menu", null);
		session.invalidate();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * 微信登陆
	 *
	 */
	@RequestMapping(value = "/wxLogin",produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "微信登陆")
	public ModelAndView wxlogin(HttpServletRequest request,HttpSession session){

		ModelAndView mav = new ModelAndView();
		mav.setViewName("wxLogin");
		return mav;
	}
}