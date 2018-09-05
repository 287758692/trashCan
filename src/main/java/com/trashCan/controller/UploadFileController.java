package com.trashCan.controller;

import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.utils.UsersConfigUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
	
	private static Logger logger = Logger.getLogger(UploadFileController.class);
	
	@RequestMapping(value="/file",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    @SystemControllerLog(value = "文件上传")
	public void fildUpload(@RequestParam(value="file",required=false) MultipartFile[] file,  
            				HttpServletRequest request ,
            				HttpServletResponse response
            )throws Exception{
        for (MultipartFile mf : file) {  
            if(!mf.isEmpty()){
            	String fileName = mf.getOriginalFilename();
            	Date d = new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS_");
            	//图片路径
            	UsersConfigUtils config = new UsersConfigUtils();
    			String path = config.GetUsersConfig("USER-IMG-PATH");
				String name = sdf.format(d);
				String fileNameAll = name+fileName;
            	logger.info(path);
            	File targetFile = new File(path, fileNameAll);  
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}else{
					targetFile.delete();
					targetFile.mkdirs();
				}
                mf.transferTo(targetFile);
                
                backInfo(response, true, 0, fileNameAll);
            }  else{
            	backInfo(response, false, -1, "");
            }
        }  
		
		return;
    }
	
    /**
     * 返回json信息
     * @param response
     * @param flag
     * @param message
     * @param fileName
     */
    private void backInfo(HttpServletResponse response, boolean flag, int message,
            String fileName) {
        String json  = "";
        //json=fileName;
        if (flag) {
            json = "{ \"status\": \"success";
        } else {
            json = "{ \"status\": \"error";
        }
        json += "\",\"fileName\": \"" + fileName + "\",\"message\": \"" + message + "\"}";
        try {
            //response.setContentType("text/javascript");
            response.setContentType("text/html; charset=utf-8");
            response.getWriter().print(json);
            logger.info(json.toString());
        } catch (IOException e) {
        	logger.error(e.getMessage(), e);
        }
    }
}
