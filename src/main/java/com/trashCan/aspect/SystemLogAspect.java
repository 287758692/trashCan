package com.trashCan.aspect;

import org.aspectj.lang.JoinPoint;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;    
import org.springframework.web.context.request.ServletRequestAttributes;    
import javax.annotation.Resource;    
import javax.servlet.http.HttpServletRequest;    
import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.trashCan.annotation.SystemControllerLogAnnotationParse;
import com.trashCan.model.SysLog;
import com.trashCan.model.SysUser;
import com.trashCan.service.SysLogService;

public class SystemLogAspect {    

	@Resource
	private SysLogService sysLogService;
	
    //本地异常日志记录对象    
	private final static Logger logger = Logger.getLogger(SystemLogAspect.class);
	
    /**  
     * 前置通知 用于拦截Controller层记录用户的操作  
     *  
     * @param joinPoint 切点  
     */    
     public void doBefore(JoinPoint joinPoint) {    
    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        SysUser user = (SysUser) session.getAttribute("User");    
        //请求的IP    
        String ip = request.getRemoteAddr();   
        
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
         try {   
        	//得到该方法描述
             String methodDesc = SystemControllerLogAnnotationParse.parse(targetClass, methodName);
             if (!methodDesc.equals("")) {
	            //*========控制台输出=========*//    
	        	logger.info("=====前置通知开始=====");    
	        	logger.info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
	        	logger.info("方法描述:" + methodDesc);    
	        	logger.info("请求人:" + user.getUserName());    
	        	logger.info("请求IP:" + ip);    
	            //*========数据库日志=========*//    
	            SysLog log = new SysLog();    
	            log.setDescription(methodDesc);    
	            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
	            log.setType(0);    
	            log.setRequestIp(ip);    
	            log.setExceptionCode( null);    
	            log.setExceptionDetail( null);    
	            log.setParams( null);    
	            log.setCrtOptr(user.getUserId());    
	            log.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));    
	            //保存数据库    
	            sysLogService.insertSysLog(log);
	            logger.info("=====前置通知结束=====");  
             }
        }  catch (Exception e) {    
            //记录本地异常日志    
            logger.error("==前置通知异常==");    
            logger.error("异常信息:{}"+e.getMessage());    
        }    
    }    
    
    /**  
     * 异常通知 用于拦截service层记录异常日志  
     *  
     * @param joinPoint  
     * @param e  
     */    
     public void doAfterThrowing(JoinPoint joinPoint, Exception e) {    
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        //读取session中的用户    
        SysUser user = (SysUser) session.getAttribute("User");   
        //获取请求ip    
        String ip = request.getRemoteAddr();    
        //获取用户请求方法的参数并序列化为JSON格式字符串    
        String params = "";    
//         if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {    
//             for ( int i = 0; i < joinPoint.getArgs().length; i++) {    
//            	 
//                params += joinPoint.getArgs()[i] + ";";    
//            }    
//        }  
         
         Class targetClass = joinPoint.getTarget().getClass();
         String methodName = joinPoint.getSignature().getName();
         
         try {
        	//得到该方法的访问权限
             String methodDesc = SystemControllerLogAnnotationParse.parse(targetClass, methodName);
             if (!methodDesc.equals("")) {
	              /*========控制台输出=========*/    
	        	logger.info("=====异常通知开始=====");
	        	logger.info("异常代码:" + e.getClass().getName());   
	        	logger.info("异常信息:" + e.getMessage());    
	        	logger.info("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
	        	logger.info("方法描述:" + methodDesc);    
	        	logger.info("请求人:" + user.getUserName());    
	        	logger.info("请求IP:" + ip);    
	        	logger.info("请求参数:" + params);    
	        	
	        	//错误信息
	        	String errorDetail = ExceptionUtils.getFullStackTrace(e);
	        	byte bt[] = new byte[1024];
	        	bt = errorDetail.getBytes();
				//写日志文件
	        	String dir = request.getServletContext().getRealPath("");
	        	String path = "Upload\\sysLog\\"+user.getUserId()+"\\";
	        	String filename = java.util.UUID.randomUUID().toString().replace("-","").substring(0, 30)+".txt";
	        	File file = new File(dir+path,filename);
	        	if (!file.exists()) {
	        		file.getParentFile().mkdirs();
	        		file.createNewFile();
				}
	        	FileOutputStream out = new FileOutputStream(file);
	        	try {
	        		out.write(bt,0,bt.length);
	        		out.close();
	        		out.flush();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
	               /*==========数据库日志=========*/    
	        	SysLog log = new SysLog();     
	            log.setDescription(methodDesc);    
	            log.setExceptionCode(e.getClass().getName());    
	            log.setType(1);    
	            log.setExceptionDetail(path+filename);    
	            log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));    
	            log.setParams(params);    
	            log.setCrtOptr(user.getUserId());    
	            log.setCrtTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));    
	            log.setRequestIp(ip);    
	            //保存数据库    
	            sysLogService.insertSysLog(log); 
	            logger.info("=====异常通知结束=====");  
             }
        }  catch (Exception ex) {    
            //记录本地异常日志    
            logger.error("==异常通知异常==");    
            logger.error("异常信息:{}"+ex.getMessage());    
        }    
         /*==========记录本地异常日志==========*/    
        logger.error("异常方法:{}异常代码:{}异常信息:{}参数:{}"+joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName()+ e.getClass().getName()+e.getMessage()+ params);    
    
    }    
  
}    