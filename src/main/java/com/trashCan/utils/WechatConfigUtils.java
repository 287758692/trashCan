package com.trashCan.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WechatConfigUtils {

	public String GetWxConfig(String type){  
		String vInfo = "";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("wx.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
				vInfo = p.getProperty(type);	
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		return vInfo;
	}  
}
