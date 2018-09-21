package com.trashCan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trashCan.annotation.SystemControllerLog;
import com.trashCan.model.Trashcanmaster;
import com.trashCan.service.TrashcanmasterService;
import com.trashCan.utils.UsersConfigUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/upload")
public class UploadFileController {
	private static Logger logger = Logger.getLogger(UploadFileController.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private TrashcanmasterService trashcanmasterService;

	@RequestMapping(value="/file",method=RequestMethod.POST,produces="application/json;charset=UTF-8")
    @SystemControllerLog(value = "文件上传")
	public void file(@RequestParam("picFile") MultipartFile[] file,
            				HttpServletRequest request ,
            				HttpServletResponse response
            )throws Exception{
	    //初始化参数
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String pathList[] = {"","","","",""};
        int i = 0;
        boolean isSuc = true;
        //读取原图片路径
        Trashcanmaster trashcanmaster = trashcanmasterService.getTrashcanmaster(Integer.parseInt(request.getParameter("id")));

        if(trashcanmaster.getPic1() != null && trashcanmaster.getPic1().length() != 0){
            pathList[i] = trashcanmaster.getPic1();
            i = i+1;
        };
        if(trashcanmaster.getPic2() != null && trashcanmaster.getPic2().length() != 0){
            pathList[i] = trashcanmaster.getPic2();
            i = i+1;
        };
        if(trashcanmaster.getPic3() != null && trashcanmaster.getPic3().length() != 0){
            pathList[i] = trashcanmaster.getPic3();
            i = i+1;
        };
        if(trashcanmaster.getPic4() != null && trashcanmaster.getPic4().length() != 0){
            pathList[i] = trashcanmaster.getPic4();
            i = i+1;
        };
        if(trashcanmaster.getPic5() != null && trashcanmaster.getPic5().length() != 0){
            pathList[i] = trashcanmaster.getPic5();
            i = i+1;
        };

        for (MultipartFile mf : file) {
            if(!mf.isEmpty()){
            	String fileName = mf.getOriginalFilename();
            	Date d = new Date();
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");
            	//图片路径
            	UsersConfigUtils config = new UsersConfigUtils();
    			String path = config.GetUsersConfig("USER-IMG-PATH");
				String name = sdf.format(d)+String.valueOf(i);
				String fileNameAll = name+fileName.substring(fileName.lastIndexOf("."));
            	logger.info(path);
            	File targetFile = new File(path, fileNameAll);  
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}else{
					targetFile.delete();
					targetFile.mkdirs();
				}
                mf.transferTo(targetFile);
                pathList[i] = fileNameAll;
				i = i+1;
            }  else{
                isSuc = false;
            }
        }
        if (isSuc){
            trashcanmaster.setPic1(pathList[0]);
            trashcanmaster.setPic2(pathList[1]);
            trashcanmaster.setPic3(pathList[2]);
            trashcanmaster.setPic4(pathList[3]);
            trashcanmaster.setPic5(pathList[4]);
            boolean result = trashcanmasterService.updateTrashcanmaster(trashcanmaster);
            resultMap.put("result",result);
        }
        resultMap.put("isSuc",isSuc);
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(objectMapper.writeValueAsString(resultMap));
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
    @SystemControllerLog(value = "文件删除")
    public void deleteFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Trashcanmaster trashcanmaster = trashcanmasterService.getTrashcanmaster(Integer.parseInt(request.getParameter("id")));
        //图片路径
        UsersConfigUtils config = new UsersConfigUtils();
        String path = config.GetUsersConfig("USER-IMG-PATH");
        File file = new File(path+request.getParameter("path"));
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        boolean isSuc = true;
        if (file.exists() && file.isFile()) {
            boolean status = file.delete();
            if (status){
                if(request.getParameter("path").equals(trashcanmaster.getPic1())){
                    trashcanmaster.setPic1("");
                };
                if(request.getParameter("path").equals(trashcanmaster.getPic2())){
                    trashcanmaster.setPic2("");
                };
                if(request.getParameter("path").equals(trashcanmaster.getPic3())){
                    trashcanmaster.setPic3("");
                };
                if(request.getParameter("path").equals(trashcanmaster.getPic4())){
                    trashcanmaster.setPic4("");
                };
                if(request.getParameter("path").equals(trashcanmaster.getPic5())){
                    trashcanmaster.setPic5("");
                };
                boolean result = trashcanmasterService.updateTrashcanmaster(trashcanmaster);
            } else {
                isSuc = false;
            }
        } else {
            isSuc = false;
        }
        String json  = "{\"fileName\": \"" + isSuc + "\"}";
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().print(json);
    }
}
