//保存选中菜单的ID
function saveMenuId(pmenuId,submenuId){
	$.ajax({
		type: "POST",
	    url: ctx+'/login/saveMenuId',
	    data: {pmenuId:pmenuId,submenuId:submenuId},
	    dataType: "json",
	    success: function(data){
    		return true;
	    },
	    error:function(){
	    	return false; 
	    }
	});
}
//保存cookies
function SetCookie(name,value){  
    var Days = 1;   //cookie 将被保存一年  
    var exp  = new Date();  //获得当前时间  
    exp.setTime(exp.getTime() + Days*24*60*60*1000);  //换成毫秒  
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();  
}  
//获取cookies
function GetCookie(name){  
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));  
    if(arr != null){  
     return unescape(arr[2]);   
    }else{  
     return null;  
    }  
}
//删除cookies
function DelCookie(name){  
    var exp = new Date();  //当前时间  
    exp.setTime(exp.getTime() - 1);  
    var cval=GetCookie(name);  
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();  
}  
//禁止用F5键 
document.onkeydown = function(){
	 if(event.keyCode==116) {
	 event.keyCode=0;
	 event.returnValue = false;
	 }
}
//禁止右键弹出菜单  
document.oncontextmenu = function(){
	return   false;    
}


function demoUploadBtn (file){
	
	var files = $("#"+file).val();
	var strFileName=files.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
	var FileExt=files.replace(/.+\./,"");   //正则表达式获取后缀
	var fileName = strFileName+"."+FileExt;
	
	$.ajaxFileUpload({
		url : ctx+"/User/upload",
		secureuri : false,
		fileElementId : file,
		type : 'post',
		dataType : 'HTML',
		data:{"filename":fileName},
		success : function(data){
			$('#uploadFile').modal('hide');
			swal("成功", obj.errMsg, "success"); 
		},
		error : function(data, status, e){
			$('#uploadFile').modal('hide');
			swal("失败", e, "error"); 
		}
	});
}
