var num = 0;
var arr = new Array();
$(document).ready(function () {
    //下拉框初始化
    combobox();
	//表单校验及提交
	confirmForm();
	//图片上传
    fileUpload();
});

function combobox(){
    $.ajax({
        url : ctx+"/trashcantypeController/type",
        type : "post",
        dataType : "json",
        success:function(data){
            $.each(data, function (i, item) {
                $("#type").append("<option value='" + item.id + "'>" + item.name + "</option>");
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
    $("#trashcanAddForm").validate({
      submitHandler : function() {  //验证通过后的执行方法
          //当前的form通过ajax方式提交（用到jQuery.Form文件）
          var param = $("#trashcanAddForm").serialize() +"&pic1="+arr[1]+"&pic2="+arr[2]+"&pic3="+arr[3]+"&pic4="+arr[4]+"&pic5="+arr[5];
          $.ajax({
              url : ctx+"/trashcanmasterController/trashCanAddConfirm",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  if(data.isSuc){
                	  document.getElementById("trashcanAddForm").reset();
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
        },
      },  
      messages : {  
    	  code : {
	            required : "不能为空!",
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
    }).oncl;
  }
function fileUpload(){
    $("#myId").dropzone({
        url: ctx+"/upload/file",
        method: 'post',
        maxFiles:5,
        maxFilesize: 10,
        filesizeBase: 1024,
        addRemoveLinks: true,
        acceptedFiles: ".jpg,.jpeg,.png,.gif",//支持的格式
        autoProcessQueue:false,
        dictDefaultMessage:'点击上传',
        dictMaxFilesExceeded: "您最多只能上传5个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFileTooBig:"文件过大上传文件最大支持.",
        dictRemoveFile: "删除",
        dictCancelUploadConfirmation: "取消",
        success: function(file,data) {
            var v = JSON.parse(data);
            num = num + 1;
            arr[num] = v.fileName;
        }
    });
}