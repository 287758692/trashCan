$(document).ready(function () {          
	//初始化List
	initTable();  
}); 

function initTable(){
	
	$('#orgDg').bootstrapTable('destroy');  
	
	$('#orgDg').bootstrapTable({
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
	    url: ctx+"/sysOrgController/orgList",//这个接口需要处理bootstrap table传递的固定参数
	    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	    //设置为limit可以获取limit, offset, search, sort, order  
	    queryParamsType : "undefined",  
        queryParams: function queryParams(params) {   //设置查询参数  
            var param = {    
                pageNumber: params.pageNumber,    
                pageSize: params.pageSize,
                orgName : $("#orgName").val()
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
	        field: 'orgId',
	        title: '序号',
	        align: 'center',
	        formatter:function(value,row,index){  
	        	if(updateBtn!="" && updateBtn!=null){
	        		var e = '<a class="btn btn-xs btn-warning" onclick="updateOrg(\''+ row.orgId +'\')">修改</a>'; 
	        	}else{
	        		var e = ''; 
	        	}
	        	if(deleteBtn!="" && deleteBtn!=null){
	        		var d = '<a class="btn btn-xs btn-danger"  onclick="deleteOrg(\''+ row.orgId +'\',\''+ row.orgName +'\')">删除</a>';
	        	}else{
	        		var d = ''; 
	        	}
	        	if(searchBtn!="" && searchBtn!=null){
	        		var s = '<a class="btn btn-xs btn-success" onclick="searchOrgUser(\''+ row.orgId +'\',\''+ row.orgName +'\')">查看成员</a>'; 
	        	}else{
	        		var r = ''; 
	        	}
                return e+d+s;
             }
	    },{
	        field: 'orgCode',
	        title: '机构代码',
	        align: 'center'
	    }, {
	        field: 'orgName',
	        title: '名称',
	        align: 'center'
	    }, {
	        field: 'orgDesc',
	        title: '描述',
	        align: 'center'
	    },{
	        field: 'crtOptr',
	        title: '操作人',
	        align: 'center'
	    }, {
	        field: 'crtTime',
	        title: '操作时间',
	        align: 'center'
	    }]
	});
}

function updateOrg(orgId){
	window.location.href = ctx+updateBtn+"?orgId="+orgId;
}

function deleteOrg(orgId,orgName){
	swal({title: '您确认要删除吗？',
		  text: "您确认要删除机构["+orgName+"]？",
		  type: 'warning',
		  showCancelButton: true,
		  closeOnConfirm: false,
		  cancelButtonText:"取消",
		  confirmButtonText: '确认删除',
		  confirmButtonColor:"#ec6c62"
		  },function (){
				$.ajax({
			        url : ctx+deleteBtn+"?orgId="+orgId,
			        type : "post",
			        dataType : "json"
					}).done(function (data){
						swal({title: '成功',
	                		  text: data.errMsg,
	                		  type: 'success',
	                		  confirmButtonText: '确定',
	                      },function(){
	                    	  location.reload();
	                      }); 
					}).error(function (data){
						swal({title: '失败',
	                		  text: data.errMsg,
	                		  type: 'error',
	                		  confirmButtonText: '确定',
	                      },function(){
	                    	  location.reload();
	                      }); 
			    });
		  })
}

function searchOrgUser(orgId,orgName){
	$('#userDg').bootstrapTable('destroy');  
	$('#userOrgId').val(orgId); 
	if (orgName != null && orgName != "") {
		$('#userOrgName').val(orgName); 
	}
	$('#userDg').bootstrapTable({
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
	    url: ctx+searchBtn+"?orgId="+orgId,//这个接口需要处理bootstrap table传递的固定参数
	    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	    //设置为limit可以获取limit, offset, search, sort, order  
	    queryParamsType : "undefined",  
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	    //search: true,           //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    //strictSearch: true,
	    //showColumns: true,      //是否显示所有的列
	    //showRefresh: true,      //是否显示刷新按钮
	    minimumCountColumns: 2,   //最少允许的列数
	    clickToSelect: false,     //是否启用点击选中行
	    searchOnEnterKey: true,
	    columns: [{
	        field: 'userOrgId',
	        title: '序号',
	        align: 'center',
	        formatter:function(value,row,index){  
	        	if(outBtn!="" && outBtn!=null){
	        		var e = '<a class="btn btn-xs btn-danger" onclick="deleteUser(\''+ row.userOrgId +'\',\''+ row.userName +'\')">删除成员</a>'; 
	        	}else{
	        		var e = ''; 
	        	}
                return e;
             }
	    },{
	        field: 'userName',
	        title: '姓名',
	        align: 'center'
	    }, {
	        field: 'roleName',
	        title: '角色',
	        align: 'center'
	    }, {
	        field: 'userCode',
	        title: '工号',
	        align: 'center'
	    }, {
	        field: 'mobileNo',
	        title: '电话',
	        align: 'center'
	    }]
	});
}
function deleteUser(userOrgId,userName){
	swal({title: '您确认要删除吗？',
		  text: "您确认要删除成员["+userName+"]？",
		  type: 'warning',
		  showCancelButton: true,
		  closeOnConfirm: false,
		  cancelButtonText:"取消",
		  confirmButtonText: '确认删除',
		  confirmButtonColor:"#ec6c62"
		  },function (){
				$.ajax({
			        url : ctx+outBtn+"?userOrgId="+userOrgId,
			        type : "post",
			        dataType : "json"
					}).done(function (data){
						swal({title: '成功',
	                		  text: data.errMsg,
	                		  type: 'success',
	                		  confirmButtonText: '确定',
	                      },function(){
	                    	  searchOrgUser($('#userOrgId').val(),"");
	                      }); 
					}).error(function (data){
						swal({title: '失败',
	                		  text: data.errMsg,
	                		  type: 'error',
	                		  confirmButtonText: '确定',
	                      },function(){
	                    	  searchOrgUser($('#userOrgId').val(),"");
	                      }); 
			    });
		  })
}

function initInUser(){
	console.log("userInDg")
	$('#userInDg').bootstrapTable('destroy');  
	$('#userInDg').bootstrapTable({
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
	    url: ctx+"/sysOrgController/orgUserInList",//这个接口需要处理bootstrap table传递的固定参数
	    //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder  
	    //设置为limit可以获取limit, offset, search, sort, order  
	    queryParamsType : "undefined",  
	    sidePagination: "server", //分页方式：client客户端分页，server服务端分页（*）
	    //search: true,           //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
	    //strictSearch: true,
	    //showColumns: true,      //是否显示所有的列
	    //showRefresh: true,      //是否显示刷新按钮
	    minimumCountColumns: 2,   //最少允许的列数
	    clickToSelect: false,     //是否启用点击选中行
	    searchOnEnterKey: true,
	    columns: [{
	        field: 'userId',
	        title: '序号',
	        align: 'center',
	        formatter:function(value,row,index){  
	        	if(inBtn!="" && inBtn!=null){
	        		var e = '<a class="btn btn-xs btn-warning" onclick="inUserConfirm(\''+ row.userId +'\',\''+ $('#userOrgId').val() +'\')">添加</a>'; 
	        	}else{
	        		var e = ''; 
	        	}
                return e;
             }
	    },{
	        field: 'userName',
	        title: '姓名',
	        align: 'center'
	    }, {
	        field: 'roleName',
	        title: '角色',
	        align: 'center'
	    }, {
	        field: 'userCode',
	        title: '工号',
	        align: 'center'
	    }, {
	        field: 'mobileNo',
	        title: '电话',
	        align: 'center'
	    }]
	});
}

function inUserConfirm(userId,orgId){
	$.ajax({
        url : ctx+inBtn+"?userId="+userId+"&orgId="+orgId,
        type : "post",
        dataType : "json",
        success:function(data){
        	$("#addUser").modal('hide');
        	searchOrgUser(orgId,"");
        }
    });
}