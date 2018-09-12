$(document).ready(function () {
    //下拉框初始化
    combobox();
	//表单校验及提交
	confirmForm();
    //多文件上传
    fileUploadBtn();
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
          var param = $("#trashcanAddForm").serialize();
          $.ajax({
              url : ctx+"/trashcanmasterController/trashCanAddConfirm",
              type : "post",
              dataType : "json",
              data: param,
              success:function(data){
                  document.getElementById("id").value = data.id;
                  if(data.isSuc){
                      //更新图片
                      $("#picFile").fileinput("upload");
                      //初始化FORM
                      document.getElementById("trashcanAddForm").reset();
                      swal({title: '成功',
                          text: data.errMsg,
                          type: 'success',
                          confirmButtonText: '确定',
                      },function(){
                          history.go(-1);
                      });
                  }else{
                  	swal("数据失败", data.errMsg, "error");
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
    });
  }

function fileUploadBtn(){
    $("#picFile").fileinput({
        language : 'zh',
        uploadUrl : ctx+"/upload/file",
        uploadAsync:false,                             //false 同步上传，
        allowedFileExtensions:  ["jpg", "jpeg", "gif", "png","bmp"],//接收的文件后缀
        showUpload: false, //是否显示上传按钮
        showRemove : false, //显示移除按钮
        showPreview : true, //是否显示预览
        showCaption: false,//是否显示标题
        browseClass: "btn btn-primary", //按钮样式
        dropZoneEnabled: true,//是否显示拖拽区域
        maxFileCount: 5, //表示允许同时上传的最大文件个数
        maxFileSize: 1024*10,//单位为kb，如果为0表示不限制文件大小
        enctype: 'multipart/form-data',
        validateInitialCount:true,
        previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
        msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
        layoutTemplates:{
            // actionDelete:"",
            actionUpload:"",
        },
        uploadExtraData: function() {   //额外参数的关键点
            return {'id':document.getElementById("id").value};
        }
    });
}