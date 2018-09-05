$(document).ready(function () {          
	//初始化List
	initTable();  
}); 

function initTable(){
	
	$('#logDg').bootstrapTable('destroy');  
	
	$('#logDg').bootstrapTable({
	    method: 'get',
	    //toolbar: '#phoneTb',    //工具按钮用哪个容器
	    striped: true,           //是否显示行间隔色
	    cache: false,            //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	    pagination: true,        //是否显示分页（*）
	    sortable: false,         //是否启用排序
	    sortOrder: "asc",        //排序方式
	    pageNumber:1,            //初始化加载第一页，默认第一页
	    pageSize: 10,            //每页的记录行数（*）
	    pageList: [10, 20],      //可供选择的每页的行数（*）
	    url: ctx+"/sysLogController/logList",//这个接口需要处理bootstrap table传递的固定参数
	    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	    //设置为limit可以获取limit, offset, search, sort, order  
	    queryParamsType : "undefined",  
        queryParams: function queryParams(params) {   //设置查询参数  
            var param = {    
                pageNumber: params.pageNumber,    
                pageSize: params.pageSize,
                status : $("#status").val()
            };    
            return param;                   
          }, 
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	    //search: true,           //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    //strictSearch: true,
	    //showColumns: true,      //是否显示所有的列
	    //showRefresh: true,      //是否显示刷新按钮
	    minimumCountColumns: 2,   //最少允许的列数
	    clickToSelect: false,     //是否启用点击选中行
	    searchOnEnterKey: true,
	    columns: [{
	        field: 'logId',
	        title: '序号',
	        align: 'center',
	        formatter:function(value,row,index){  
	        	if(row.Type!="" && row.Type!=null){
	        		var e = '<a class="btn btn-xs btn-warning" onclick="searchDetail(\''+ row.ExceptionCode +'\',\''+ row.ExceptionDetail +'\')">明细</a>';  
	        	}else{
	        		var e = ''; 
	        	}
        		return e;
             }
	    },{
	        field: 'description',
	        title: '模块',
	        align: 'center'
	    },{
	        field: 'method',
	        title: '接口',
	        align: 'center'
	    },{
	        field: 'Type',
	        title: '类型',
	        align: 'center',
	        formatter : function(value) {
				var str = "";
				if (value == "0") {
					str = '<span class="label label-sm label-success"> 正常 </span>';
				}
				if (value == "1") {
					str = '<span class="label label-sm label-danger"> 报错 </span>';
				}
				return str;
			}
	    },{
	        field: 'RequestIp',
	        title: '请求IP',
	        align: 'center'
	    },{
	        field: 'crtOptr',
	        title: '请求人',
	        align: 'center'
	    }, {
	        field: 'crtTime',
	        title: '请求时间',
	        align: 'center'
	    }]
	});
}

function searchDetail(ExceptionCode,ExceptionDetail){
	console.log(ExceptionDetail);
	var url = ctx+ExceptionDetail;
    var htmlobj= $.ajax({url:url,async:false});
    var dataString = htmlobj.responseText;
	document.getElementById("logTitle").innerHTML=ExceptionCode;
	document.getElementById("ExceptionDetail").innerHTML=dataString;
	$('#logDetail').modal('show');
}

