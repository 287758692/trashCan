$(document).ready(function () {
    //下拉框初始化
    combobox();
	//表单校验及提交
	confirmForm();
});

function combobox(){
    $.ajax({
        url : ctx+"/trashcantypeController/type",
        type : "post",
        dataType : "json",
        success:function(data){
            $.each(data, function (i, item) {
                if(item.id == type){
                    $("#type").append("<option value='" + item.id + "' selected>" + item.name + "</option>");
                    getTypeInfo();
                }else{
                    $("#type").append("<option value='" + item.id + "'>" + item.name + "</option>");
                }
            });
        }
    });
}

function getTypeInfo(){
    $.ajax({
        url : ctx+"/trashcantypeController/getTypeInfo",
        type : "post",
        dataType : "json",
        data:{id:$("#type option:selected").val()},
        success:function(data){
            $("#size").attr("value",data.size);
            $("#material").attr("value",data.material);
            $("#power").attr("value",data.power);
            $("#lifetime").attr("value",data.lifetime);
            $("#light").attr("value",data.light);
            $("#color").attr("src",data.color);

        }
    });
}

function confirmForm() {
    $("#trashCanAmdForm").validate({
      submitHandler : function() {  //验证通过后的执行方法
          //当前的form通过ajax方式提交（用到jQuery.Form文件）
      	var param = $("#trashCanAmdForm").serialize();
          $.ajax({
              url : ctx+"/trashcanmasterController/trashCanAmdConfirm",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  if(data.isSuc){
                	  document.getElementById("trashCanAmdForm").reset();
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
    	  name : {
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
    	  name : {
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

function fileUploadBtn(file,id){

    var files = $("#"+file).val();
    var strFileName=files.replace(/^.+?\\([^\\]+?)(\.[^\.\\]*?)?$/gi,"$1");  //正则表达式获取文件名，不带后缀
    var FileExt=files.replace(/.+\./,"");   //正则表达式获取后缀
    var fileName = strFileName+"."+FileExt;

    $.ajaxFileUpload({
        url : ctx+"/upload/file",
        secureuri : false,
        fileElementId : file,
        type : 'post',
        dataType : 'json',
        data:{
            "filename":fileName,
        },
        success : function(data){
            $("#"+id).val(data.fileName);
        }
    });
}