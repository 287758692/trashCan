$(document).ready(function () {   
	//权限树初始化
	UITree();
	//表单校验及提交
	confirmForm();
}); 

function UITree() {
	$('#tree').data('jstree',false);
	$.ajax({
        url : ctx+"/sysRoleController/menuAll",
        type : "post",
        dataType : "json",
        data:{roleId:$("#roleId").val()}
    }).done(function(data){
    	console.log(data);
    	$('#tree').jstree({
            'plugins': ["wholerow", "checkbox", "types"],
            'core': {
                "themes" : {
                    "responsive": false
                },    
                'data':data
            },
            "types" : {
                "default" : {
                    "icon" : "fa fa-folder icon-state-warning icon-lg"
                },
                "file" : {
                    "icon" : "fa fa-file icon-state-warning icon-lg"
                }
            }
        });
    });
}

function confirmForm() {
    $("#roleAmdForm").validate({
      submitHandler : function() {  //验证通过后的执行方法
        //当前的form通过ajax方式提交（用到jQuery.Form文件）
		  $('#tree').jstree(true).get_all_checked = function(full) {
			    var tmp=new Array;
			    for(var i in this._model.data){
			        if(this.is_undetermined(i)||this.is_checked(i)){tmp.push(full?this._model.data[i]:i);}
			    }
			    tmp.pop();
			    return tmp;
			};
			console.log($('#tree').jstree(true).get_all_checked());
		$('#roleTree').val($('#tree').jstree(true).get_all_checked());
		var param = $("#roleAmdForm").serialize();
          $.ajax({
              url : ctx+"/sysRoleController/roleAmdConfirm",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  if(data.isSuc){
                	  document.getElementById("roleAmdForm").reset(); 
                      swal({title: '成功',
                		  text: data.errMsg,
                		  type: 'success',
                		  confirmButtonText: '确定',
                      },function(){
                    	  history.go(-1);
                      }); 
                  }else{
                  	swal("失败", data.errMsg, "error"); 
                  }
                }
              });
      },
      focusInvalid : true,   //验证提示时，鼠标光标指向提示的input
      rules : {
    	  roleName : {  
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
    	  roleName : {  
	            required : "角色名称不能为空!",
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