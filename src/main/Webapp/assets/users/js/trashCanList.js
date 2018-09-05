var city;
$(document).ready(function () {
    //初始化List
    initTable();
    //
    $('#address').keyup(function(){
        $('#address').autocomplete({
            source: function (request, response){
                var url = "https://apis.map.qq.com/ws/place/v1/suggestion/";
                $.ajax({
                    type: 'GET',
                    url:url,
                    dataType:'jsonp',
                    data: {
                        "region": city,
                        "keyword": $("#address").val(),
                        "key":"PJ6BZ-B3RW6-OPMSF-E3WMZ-AOHRO-UJBGZ",
                        "output": 'jsonp'},
                    success: function (data) {
                        if(data.status == 0){
                            var result = new Array();
                            $.each(data.data, function(i, item){
                                result.push(item.address);
                            });
                            response(result);
                        }
                    }
                });
            },
            autoFocus: true
        });
    });
});

function local(){
    var geolocation = new qq.maps.Geolocation("PJ6BZ-B3RW6-OPMSF-E3WMZ-AOHRO-UJBGZ", "trashCan-MAP");
    var options = {timeout: 8000};
    geolocation.getLocation(init, null, options);
}

function init(position) {
    var Lat = position.lat;
    var Lng = position.lng;
    city = position.city;
    //设置地图中心点坐标
    var center = new qq.maps.LatLng(Lat,Lng);
    //加载地图，设置精度
    var map = new qq.maps.Map(document.getElementById('container'),{
        center: center,
        zoom: 20
    });
    //添加比例尺
    var scaleControl = new qq.maps.ScaleControl({
        align: qq.maps.ALIGN.BOTTOM_LEFT,
        margin: qq.maps.Size(85, 15),
        map: map
    });
    //添加中心标记
    var marker = new qq.maps.Marker({
        position: center,
        map: map,
        icon:new qq.maps.MarkerImage(ctx+"/assets/users/img/local.png",null,null,null,new qq.maps.Size(30, 38))
    });
    var cirle = new qq.maps.Circle({
        center: center,
        radius: 1000,
        map: map,
        strokeWeight:0,
    });
    //添加提示窗
    var info = new qq.maps.InfoWindow({ map: map });
    //获取垃圾桶坐标
    $.ajax({
        url : ctx+"/trashcanmasterController/trashCanMap",
        type : "post",
        dataType : "json",
        data : {Lat:Lat,Lng:Lng},
        success:function(result){
            //添加垃圾桶标记
            if(result.rows!=null){
                for(var i=0; i<result.rows.length; i++){
                    var data = result.rows[i];
                    var marker = new qq.maps.Marker({ position: new qq.maps.LatLng(data.lat, data.lng), map: map ,
                        icon:new qq.maps.MarkerImage(data.color,null,null,null,new qq.maps.Size(30, 38)) });    //创建标记
                    //***将必要的数据存入每一个对应的marker对象
                    marker.id = data.id;
                    marker.code = data.code;
                    marker.address = data.address;
                    qq.maps.event.addListener(marker, 'click', function() {    //获取标记的点击事件
                        info.open();  //点击标记打开提示窗
                        info.setContent('<div class="mapInfo"><p class="center">'+this.code+'</p><p>'+this.address+'</p><button type="button" class="btn green pull-left" onclick="updateTrashCan(\''+this.id+'\')">修改</button><button type="button" class="btn red pull-right" onclick="deleteTrashCan(\''+this.id+'\')">删除</button></div>');
                        info.setPosition(new qq.maps.LatLng(this.position.lat, this.position.lng));  //提示窗位置data
                    });
                }
            }else{
                //layer.open({ content: "获取垃圾桶位置失败", skin: 'msg', time: 2 });
            }
        }
    });
    //获取点击坐标
    var listener = qq.maps.event.addListener(
        map,
        'click',
        function(event) {
            //地址逆解析
            geocoder = new qq.maps.Geocoder({
                complete:function(result){
                    //alert('成功：'+result.detail.address);
                    info.open();  //点击标记打开提示窗
                    info.setContent('<div class="mapInfo"><p class="center">'+"地址:"+result.detail.address+'</p><button type="button" class="btn blue col-md-pull-3" onclick="addTrashCan(\''+event.latLng.getLat()+'\',\''+event.latLng.getLng()+'\',\''+result.detail.address+'\')">新增垃圾桶</button></div>');
                    info.setPosition(new qq.maps.LatLng(event.latLng.getLat(), event.latLng.getLng()));  //提示窗位置data
                }
            });
            geocoder.getAddress(new qq.maps.LatLng(event.latLng.getLat(),event.latLng.getLng()));
        }
    );
}

window.onload = loadScript;
function loadScript() {
    var script = document.createElement("script");
    script.type = "text/javascript";
    script.src = "https://map.qq.com/api/js?v=2.exp&callback=init";
    document.body.appendChild(script);
}

function getTrashCan(){
    var address = document.getElementById("address").value;
    var callbacks={
        complete:function(result){
            init(result.detail.location);
        },
    }
    geocoder = new qq.maps.Geocoder(callbacks);
    geocoder.getLocation(address);
}

function addTrashCan(lat,lng,address){
    window.location.href = ctx+insertBtn+"?lat="+lat+"&lng="+lng+"&address="+address;
}

function updateTrashCan(id){
	window.location.href = ctx+updateBtn+"?id="+id;
}

function deleteTrashCan(id){
    swal({title: '您确认要删除吗？',
        text: "确认删除",
        type: 'warning',
        showCancelButton: true,
        closeOnConfirm: false,
        cancelButtonText:"取消",
        confirmButtonText: '确认',
        confirmButtonColor:"#ec6c62"
    },function (){
        $.ajax({
            url : ctx+deleteBtn+"?id="+id,
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

function initTable(){

    $('#trashCanDg').bootstrapTable('destroy');

    $('#trashCanDg').bootstrapTable({
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
        url: ctx+"/trashcanmasterController/trashCanList",//这个接口需要处理bootstrap table传递的固定参数
        //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
        //设置为limit可以获取limit, offset, search, sort, order
        queryParamsType : "undefined",
        queryParams: function queryParams(params) {   //设置查询参数
            var param = {
                pageNumber: params.pageNumber,
                pageSize: params.pageSize,
                userName : $("#code").val()
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
            field: 'userId',
            title: '序号',
            align: 'center',
            formatter:function(value,row){
                if(updateBtn!="" && updateBtn!=null){
                    var e = '<a class="btn btn-xs btn-warning" onclick="updateTrashCan(\''+ row.id +'\')">修改</a>';
                }else{
                    var e = '';
                }
                if(deleteBtn!="" && deleteBtn!=null){
                    var d = '<a class="btn btn-xs btn-danger"  onclick="deleteTrashCan(\''+ row.id +'\')">删除</a>';
                }else{
                    var d = '';
                }
                return e+d;
            }
        },{
            field: 'code',
            title: '编号',
            align: 'center'
        },{
            field: 'type',
            title: '类型',
            align: 'center'
        },{
            field: 'address',
            title: '地址',
            align: 'center'
        },{
            field: 'useDate',
            title: '日期',
            align: 'center'
        },{
            field: 'color',
            title: '颜色',
            align: 'center',
            formatter: function(value,row,index){
                return '<img  src="'+row.color+'" style="width: 30px;height: auto">';
            }
        },{
            field: 'lat',
            title: '经度',
            align: 'center'
        },{
            field: 'lng',
            title: '维度',
            align: 'center'
        },{
            field: 'modOptr',
            title: '操作人',
            align: 'center'
        }, {
            field: 'modTime',
            title: '操作时间',
            align: 'center'
        }]
    });
}

