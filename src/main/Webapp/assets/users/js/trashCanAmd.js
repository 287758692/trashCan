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
                      //更新图片
                      $("#picFile").fileinput("upload");
                      //初始化FORM
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
        },
      },  
      messages : {  
    	  name : {
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
    $("#picFile").fileinput('destroy');
    var path = [];
    var con = [];
    if(pic1 !== ""){
        path[1] = ctx+"Upload/"+pic1;
        con[1] = {caption: pic1, width: "170px", url: ctx+"/upload/deleteFile?id="+document.getElementById("id").value+"&path="+pic1, key: pic1};
    };
    if(pic2 !== ""){
        path[2] = ctx+"Upload/"+pic2;
        con[2] = {caption: pic2, width: "170px", url: ctx+"/upload/deleteFile?id="+document.getElementById("id").value+"&path="+pic2, key: pic2};
    };
    if(pic3 !== ""){
        path[3] = ctx+"Upload/"+pic3;
        con[3] = {caption: pic3, width: "170px", url: ctx+"/upload/deleteFile?id="+document.getElementById("id").value+"&path="+pic3, key: pic3};
    };
    if(pic4 !== ""){
        path[4] = ctx+"Upload/"+pic4;
        con[4] = {caption: pic4, width: "170px", url: ctx+"/upload/deleteFile?id="+document.getElementById("id").value+"&path="+pic4, key: pic4};
    };
    if(pic5 !== ""){
        path[5] = ctx+"Upload/"+pic5;
        con[5] = {caption: pic5, width: "170px", url: ctx+"/upload/deleteFile?id="+document.getElementById("id").value+"&path="+pic5, key: pic5};
    };
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
        },
        overwriteInitial: false,
        initialPreviewAsData: true,
        initialPreview: path,
        initialPreviewConfig: con,
    });
}