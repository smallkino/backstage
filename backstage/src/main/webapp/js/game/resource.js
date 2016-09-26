$(function(){
  //获取服务器列表
  var url = "http://"+jQuery("#gameHost").val()+"/gmBackstage/gmGetServletList";
           
  jQuery.ajax({
       type: "post", // 或者 "get"
       url: url,
       dataType: "jsonp",
       jsonp: "str",
       success: function(data) {
    	   if(data.dataMap.serverList)
    	   for(var i = 0 ; i < data.dataMap.serverList.length;i++){
    		   var element = data.dataMap.serverList[i];
    		   jQuery('#resHost').append("<option value='"+element.ip+":"+element.port+"'>"+element.name+"</option>");
    	   }
       }
  });
  //添加
  jQuery('#add').click(function(){
	  var resType = jQuery('#resType').val();
	  var resTypeText = jQuery("#resType").find("option:selected").text();
	  var resValue = jQuery.trim(jQuery('#resValue').val());
	  var resId = jQuery.trim(jQuery('#resId').val());
	  
  	//为空
	if(resValue.length == 0){
		jQuery('#modalText').text('资源值不能为空!');
		jQuery('#myModal').modal('show');
		return;
	}
	
	//物品与被动技能时校验
	if(resType == 7 || resType == 8 || resType == 10){
		if(resId.length == 0){
			jQuery('#modalText').text('id不能为空!');
			jQuery('#myModal').modal('show');
			return;
		}
		//校验正则
		var regId = /^[1-9]\d*$/;
		if(!regId.test(resId)){
			jQuery('#modalText').text('id只能为正整数');
			jQuery('#myModal').modal('show');
			return;
		}
	}
	
	//校验正则
	var reg = /^-?[1-9]\d*$/;
	if(!reg.test(resValue)){
		jQuery('#modalText').text('操作值只能为整数');
		jQuery('#myModal').modal('show');
		return;
	}
	
	if(resType == 7 || resType == 8 || resType == 10){
		jQuery('#resList').append('<option resVal="'+resType+':'+resId+':'+resValue+'">'+resTypeText+' : '+resId+' : '+resValue+'</option>');
	}else{
		jQuery('#resList').append('<option resVal="'+resType+':'+resValue+'">'+resTypeText+' : '+resValue+'</option>');
	}
  });
  
  //撤销按钮操作
  jQuery('#remove').click(function(){
	 jQuery("#resList").find("option:selected").remove();
  });
  
  //提交
  jQuery('#submit').click(function(){
	  var requestUrl = 'http://'+jQuery('#resHost').val()+'/gmBackstage/gmUpdateResource';
	  var playName = jQuery.trim(jQuery('#playName').val());
	  var resValue = getResValue();
	  
  	//为空
	if(playName.length == 0 || resValue.length == 0){
		jQuery('#modalText').text('玩家名称或资源列表不能为空!');
		jQuery('#myModal').modal('show');
		return;
	}
	//校验长度
	if(playName.length < 2 || playName.length > 15){
		jQuery('#modalText').text('玩家名称长度不能小于2大于15!');
		jQuery('#myModal').modal('show');
		return;
	}
	//校验正则
	if(/[~#^$@%&!*]/gi.test(playName)){
		jQuery('#modalText').text('玩家名称不能包含特殊字符');
		jQuery('#myModal').modal('show');
		return;
	}
	//操作玩家资源
	jQuery.ajax({
	       type: "post", // 或者 "get"
	       url: requestUrl,
	       dataType: "jsonp",
	       data:{
	    	   'str':playName,
	    	   'str2':resValue
	       },
	       jsonp: "str3",
	       success: function(data) {
	    	   if(data.rt == 0){
	    		   jQuery('#modalText').text('操作成功!');
	    		   jQuery('#myModal').modal('show');
	    		   return;
	    	   }else if(data.rt == 20004 || data.rt == 20001){
	    		   jQuery('#modalText').text('该玩家不存在!');
	    		   jQuery('#myModal').modal('show');
	    		   return;
	    	   }else{
	    		   jQuery('#modalText').text('操作异常!');
	    		   jQuery('#myModal').modal('show');
	    		   return;
	    	   }
	       },
	       error:function(){
	    	   jQuery('#modalText').text('操作异常!');
    		   jQuery('#myModal').modal('show');
    		   return;
	       }
	  });
  });
});  

//判断添加资源是否已经存在
function validateResText(resText){
	var result = false;
	jQuery('#resList li').each(function(index,item){
		var text = jQuery(item).text();
		if(text.indexOf(resText) > -1){
			result = true;
			return;
		}
	});
	return result;
}

//获取资源列表的值
function getResValue(){
	var result = '';
	jQuery('#resList option').each(function(index,item){
		var text = jQuery(item).attr('resVal');
		if(index == 0){
			result += text;
		}else{
			result += ','+text;
		}
	});
	return result;
}

