package com.trashCan.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trashCan.annotation.SystemControllerLog;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trashCan.service.WechatService;

@Controller
@RequestMapping("/wechat")
public class WechatController {
	
	private final static Logger log = Logger.getLogger(WechatController.class);
	
	@Autowired
	private WechatService wechatservice;
	
	/**
	 * 微信平台接入
	 * @throws UnsupportedEncodingException 
	 * 
	 */
	@RequestMapping(value = "/join", method = {RequestMethod.GET, RequestMethod.POST},produces="application/json;charset=UTF-8")
	@SystemControllerLog(value = "微信平台接入")
	@ResponseBody 
	public void join(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		
		request.setCharacterEncoding("UTF-8"); 
		response.setCharacterEncoding("UTF-8");
		
        //获取微信后台传入的四个参数
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
		log.info("signature"+signature);
		log.info("timestamp"+timestamp);
		log.info("nonce"+nonce);
		log.info("echostr"+echostr);
        boolean flag = wechatservice.checkSignature(signature, timestamp, nonce);
        log.info("flag"+flag);
        try {
            if(flag){
                response.getWriter().write(echostr);//注意此处必须返回echostr以完成验证
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}