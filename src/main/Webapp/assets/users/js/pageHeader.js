$(document).ready(function () {   
	//表单校验及提交
	confirmUser();
	confirmPass();
}); 

function confirmUser() {
    $("#userInfoForm").validate({
      submitHandler : function() {  //验证通过后的执行方法
          //当前的form通过ajax方式提交（用到jQuery.Form文件）
      	var param = $("#userInfoForm").serialize();
          $.ajax({
              url : ctx+"/pageHeader/updateUser",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  if(data.isSuc){
                	  document.getElementById("userInfoForm").reset(); 
                	  $("#userinfo").modal('hide');
                      swal({title: '成功',
                		  text: data.errMsg,
                		  type: 'success',
                		  confirmButtonText: '确定',
                      },function(){
                    	  history.go(0);
                      }); 
                  }else{
                	  $("#userinfo").modal('hide');
                  	swal("失败", data.errMsg, "error"); 
                  }
                }
              });
      },
      focusInvalid : true,   //验证提示时，鼠标光标指向提示的input
      rules : {
    	  userName : {  
	            required : true,   //验证非空
	//            remote: {          //远程ajax验证
	//                url: "../xxxx/checkaccount", //检查是否存在账号，存在则返回true
	//                type: "GET",
	//                dataType: "json",
	//                data: {
	//                    account: function () {
	//                        return $("#account").val(); //这个是取要验证的用户名
	//                    }
	//                },
	//                dataFilter: function (data) {  //判断控制器返回的内容
	//                    var notice = eval("("+ data +")");
	//                    if( notice ){
	//                        return false;
	//                    }else{
	//                        return true;
	//                    }
	//                }
	//            }
        },  
      },  
      messages : {  
    	  userName : {  
	            required : "不能为空!",
//	            remote: "用户名已存在！"  //这个地方如果不写的话，是自带的提示内容，加上就是这个内容。
        }
      },  
      errorElement : "small",
      errorClass : "font-red",
      highlight : function(element, errorClass,validClass) {
        $(element).closest('.form-control').addClass('border-red');
      },
      success : function(element) {
        $(element).siblings('.form-control').removeClass('border-red');
        $(element).remove();
      }
    });
  }

function confirmPass() {
    $("#ChangePasswordModalForm").validate({
      submitHandler : function() {  //验证通过后的执行方法
          //当前的form通过ajax方式提交（用到jQuery.Form文件）
      	var param = {puserId:$("#puserId").val(),CurrentPassword:md5($("#CurrentPassword").val()),NewPassword:md5($("#NewPassword").val()),NewPasswordRepeat:md5($("#NewPasswordRepeat").val())};
          $.ajax({
              url : ctx+"/pageHeader/updatePass",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  if(data.isSuc){
                	  document.getElementById("ChangePasswordModalForm").reset(); 
                	  $("#changepassword").modal('hide');
                      swal({title: '成功',
                		  text: data.errMsg,
                		  type: 'success',
                		  confirmButtonText: '确定',
                      },function(){
                    	  history.go(0);
                      }); 
                  }else{
                	  $("#changepassword").modal('hide');
                  	swal("失败", data.errMsg, "error"); 
                  }
                }
              });
      },
      focusInvalid : true,   //验证提示时，鼠标光标指向提示的input
      rules : {
    	  CurrentPassword : {  
	            required : true,   //验证非空
	            rangelength : [6,32],   //验证非空
	            remote: {          //远程ajax验证
	                url: ctx+"/pageHeader/checkPass", //检查是否存在账号，存在则返回true
	                type: "GET",
	                dataType: "json",
	                data: {
	                	userId: function () {
	                        return $("#userId").val(); //这个是取要验证的用户名
	                    },
	                	passwd: function () {
	                        return md5($("#CurrentPassword").val()); //这个是取要验证的用户名
	                    }
	                },
	                dataFilter: function (data) {  //判断控制器返回的内容
	                    var notice = eval("("+ data +")");
	                    console.log(notice)
	                    if( notice ){
	                        return true;
	                    }else{
	                        return false;
	                    }
	                }
	            }
        },  
        NewPassword : {  
            required : true,   //验证非空
            rangelength : [6,32],   //验证非空
            notEqualTo : "#CurrentPassword",
        },
        NewPasswordRepeat : {  
            required : true,   //验证非空
            rangelength : [6,32],   //验证非空
            equalTo: "#NewPassword",
        },
      },  
      messages : {  
    	  CurrentPassword : {  
	            required : "原密码不能为空!",
	            rangelength:"请输入6-32位密码",
	            remote: "原密码错误！"  //这个地方如果不写的话，是自带的提示内容，加上就是这个内容。
    	  },
    	  NewPassword : {  
	          required : "新密码不能为空!",
	          rangelength:"请输入6-32位密码",
	          notEqualTo:"不能与当前密码相同！",
	      },
	      NewPasswordRepeat : {  
	          required : "确认密码不能为空!",
	          rangelength:"请输入6-32位密码",
	          equalTo: "两次输入密码不一致！"  //这个地方如果不写的话，是自带的提示内容，加上就是这个内容。
	      }
      },  
      errorElement : "small",
      errorClass : "font-red",
      highlight : function(element, errorClass,validClass) {
        $(element).closest('.form-control').addClass('border-red');
      },
      success : function(element) {
        $(element).siblings('.form-control').removeClass('border-red');
        $(element).remove();
      }
    });
  }

function md5(input){
	return hex_md5(input);
}