var city;
$(document).ready(function () {
    var bind_name = 'input';
    if (navigator.userAgent.indexOf("MSIE") != -1){
        bind_name = 'propertychange';
    }
    $('#address').bind(bind_name, function(){
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
        url : ctx+"/WxTrashCanController/trashCanList",
        type : "post",
        dataType : "json",
        data : {city:city},
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
                    qq.maps.event.addListener(marker, 'click', function(evt) {    //获取标记的点击事件
                        info.open();  //点击标记打开提示窗
                        info.setContent('<div class="mapInfo" align="center"><p class="center">'+"编号："+evt.target.code+'</p><p>'+evt.target.address+'</p><div class="row"><button type="button" class="btn blue col-md-4 col-sm-4" onclick="updateTrashCan(\''+evt.target.id+'\')">修改</button>&nbsp;&nbsp;<button type="button" class="btn red col-md-4 col-sm-4" onclick="deleteTrashCan(\''+evt.target.id+'\')">删除</button></div></div>');
                        info.setPosition(evt.latLng);  //提示窗位置data
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
                    info.setContent('<div class="mapInfo" align="center"><p class="center">'+result.detail.address+'</p><button type="button" class="btn blue" onclick="addTrashCan(\''+event.latLng.getLat()+'\',\''+event.latLng.getLng()+'\',\''+result.detail.address+'\')">新增垃圾桶</button></div>');
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
    window.location.href = ctx+"/WxTrashCanController/trashCanAdd"+"?lat="+lat+"&lng="+lng+"&address="+address;
}

function updateTrashCan(id){
	window.location.href = ctx+"/WxTrashCanController/trashCanAmd"+"?id="+id;
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
            url : ctx+"/WxTrashCanController/trashCanDel"+"?id="+id,
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

