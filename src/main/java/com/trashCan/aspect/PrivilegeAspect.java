package com.trashCan.aspect;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.trashCan.service.SysRoleMenuService;

import com.trashCan.annotation.PrivilegeAnnotationParse;
import com.trashCan.model.SysUser;

/**
 * 权限检查切面
 * 根据用户原有的权限，与目标方法的权限配置进行匹配，
 * 如果目标方法需要的权限在用户原有的权限以内，则调用目标方法
 * 如果不匹配，则不调用目标方法
 * @author Minhellic
 *
 */
@SuppressWarnings({ "rawtypes" })
public class PrivilegeAspect {
	
	private final static Logger log = Logger.getLogger(PrivilegeAspect.class);
	@Resource
	private SysRoleMenuService sysRoleMenuService;
	
    /**
     * aop中的环绕通知
     * 在这个方法中检查用户的权限和目标方法的需要的权限是否匹配
     * 如果匹配则调用目标方法，不匹配则不调用
     * @param joinPoint　连接点
     * @throws Throwable
     */
    public Object isAccessMethod(ProceedingJoinPoint joinPoint) throws Throwable {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession();    
        
        Object result = new Object();
    	boolean isAccessed = false;
        /**
         * 1.获取访问目标方法应该具备的权限
         *  为解析目标方法的PrivilegeInfo注解，根据我们定义的解析器，需要得到：目标类的class形式　方法的名称
         */
        Class targetClass = joinPoint.getTarget().getClass();
        String methodName = joinPoint.getSignature().getName();
        //得到该方法的访问权限
        String methodAccess = PrivilegeAnnotationParse.parse(targetClass, methodName);
        /*
         * 如果目标方法没有使用PrivilegeInfo注解，则解析出来的权限字符串就为空字符串
         * 则不校验权限
         */
        if ("".equals(methodAccess)) {
            isAccessed = true;
        }else {
        	SysUser sysUser = (SysUser) session.getAttribute("User");
        	if (sysUser==null) {
        		isAccessed = false;
    		} else {
    	        /*
    	         * 遍历用户的权限，看是否拥有目标方法对应的权限
    	         */
    	        for (Map<String, Object> privilege : sysRoleMenuService.rolePrivilege(sysUser.getRoleId())) {

    	            /*
    	             * 用户原有权限列表中有的权限与目标方法上PrivilegeInfo注解配置的权限进行匹配
    	             */
    	            if (privilege.get("menuId") != null && privilege.get("menuId").toString().equalsIgnoreCase(methodAccess)) {
    	                isAccessed = true;
    	                break;
    	            }
    	        }
    		}
		}
        /*
         * 如果用户拥有权限，则调用目标方法　，如果没有，则不调用目标方法，只给出提示
         */
        if (isAccessed) {
        	result = joinPoint.proceed();//调用目标方法
        	log.info("权限正确");
        } else {
    		ModelAndView mav = new ModelAndView();
    		mav.setViewName("error");
        	result = mav ;
        	log.info("权限错误");
        }
        return result;
    }
}