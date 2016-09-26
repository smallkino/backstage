var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
jQuery(function(){
	 $('.form_datetime').datetimepicker({
	        //language:  'fr',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
	        showMeridian: 1
	    });
	 
	 //历史邮件改变事件
	 jQuery('#mailList').change(function(){
		 var mailId = jQuery(this).val();
		 if(mailId.length > 0 ){
			 loadHistoryMail(mailId);
		 }
	 });
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
	    		   jQuery('#sendHost').append("<option value='"+element.ip+":"+element.port+"'>"+element.name+"</option>");
	    	   }
	       }
	  });
	
	  //                                         
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
  
  //点击保存邮件
  jQuery('#saveMail').click(function(){
	  var data={};
	  var flag = setAndValidate(data);
	  if(flag){
		//保存请求
		  jQuery.ajax({
			   type: "post", // 或者 "get"
		       url: "/"+contextPath+"/mail/saveOrUpdate",
		       data:data,
		       success: function(data) {
		    	   if(data.success){
		    		   jQuery('#modalText').text("保存成功");
		    		   jQuery('#myModal').on('hide.bs.modal',function(){
		    			   window.location.reload(true);
		    		   })
		    	   }else{
		    		   jQuery('#modalText').text(data.message);
		    	   }
		    	   jQuery('#myModal').modal('show');
		       }
		  });
	  }
  });
  //点击定时发送
	jQuery('#sendMailTime').click(function(){
		jQuery('#myModalDate').on('hidden.bs.modal',function(){
			jQuery('#dtp_input1').val(jQuery.trim(jQuery('#time').val()));
			var dateStr = jQuery.trim(jQuery('#dtp_input1').val());
			
			if(dateStr.length == 0){
				jQuery('#modalText').text('时间不能为空!');
				jQuery('#myModal').modal('show');
				return;
			}
			if(!isDateTime(dateStr)){
				jQuery('#modalText').text('日期格式错误!');
				jQuery('#myModal').modal('show');
				return;
			}
			var data={};
			var flag = setAndValidate(data);
			data.isTime = true;
			data.sendDate = dateStr;
			delete data['id'];
			if(flag){
				jQuery.ajax({
					   type: "post", // 或者 "get"
				       url: "/"+contextPath+"/mail/saveOrUpdate",
				       data:data,
				       success: function(data) {
				    	   if(data.success){
				    		   jQuery('#modalText').text("邮件默认将会在指定时间发送,可以在定时邮件列表中进行操作!");
				    		   jQuery('#myModal').on('hidden.bs.modal',function(){
				    			   window.location.reload(true);
				    		   })
				    	   }else{
				    		   jQuery('#modalText').text(data.message);
				    	   }
				    	   jQuery('#myModal').modal('show');
				       }
				  });
			}
		})
		jQuery('#myModalDate').modal('show');
		
	});
	//点击定时邮件列表
	jQuery('#timeMail').click(function(){
		jQuery('#myModalTime').modal('show');
	});
	
	//删除邮件按钮
	jQuery('#removeMail').click(function(){
		var id = jQuery("#timeMailList").find("option:selected").val();
		jQuery.ajax({
			type:"post",
			url:"/"+contextPath+"/mail/deleteById",
			data:{"id":id},
			dataType:"json",
			success:function(data){
				if(data.success){
					jQuery("#timeMailList").find("option:selected").remove();
				}
			}
		});
	});
	
	//立即发送按钮
	jQuery('#sendMailNow').click(function(){
		var data = {};
		var flag = setAndValidate(data);
		if(flag){
			jQuery('#myModalConfim').modal('show');
		}
	});
	
	//确认发送邮件按钮
	jQuery('#confimSend').click(function(){
		jQuery('#myModalConfim').modal('hide');
		var data = {};
		setAndValidate(data);
		jQuery.ajax({
			type:'post',
			url:"/"+contextPath+"/mail/sendMailNow",
			data:data,
			dataType:"json",
			success:function(data){
				if(data.success){
					jQuery('#modalText').text("发送成功!");
					jQuery('#myModal').on('hidden.bs.modal',function(){
						window.location.reload(true);
		    		})
				}else{
					jQuery('#modalText').text(data.message);
				}
				jQuery('#myModal').modal('show');
			}
		});
	});
	//取消发送邮件按钮
	jQuery('#cancelSend').click(function(){
		jQuery('#myModalConfim').modal('hide');
	});
});

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

//载入历史邮件
function loadHistoryMail(mailId){
	jQuery.ajax({
		type:"post",
		url:"/"+contextPath+"/mail/getMailById",
		data:{"id":mailId},
		dataType:"json",
		success:function(data){
			 //标题
			 jQuery('#title').val(data.data.title)
			  //内容
			 jQuery('#content').val(data.data.content);
			  //附件
			 jQuery('#content').val(data.data.content);
			 jQuery('#resList').html('');
			 if(data.data.resList && data.data.resList.length > 0){
				 var resArray = data.data.resList.split(",");
				 for(var i = 0 ; i < resArray.length ;i++){
					 var resVal = resArray[i].split(":");
					 var resTypeText = jQuery("#resType").find("[value='"+resVal[0]+"']").text();
					 if(resVal.length == 2){
						 jQuery('#resList').append('<option resVal="'+resVal[0]+':'+resVal[1]+'">'+resTypeText+' : '+resVal[1]+'</option>');
					 }else{
						 jQuery('#resList').append('<option resVal="'+resVal[0]+':'+resVal[1]+':'+resVal[2]+'">'+resTypeText+' : '+resVal[1]+' : '+resVal[2]+'</option>');
					 }
				 }
			 }
			  //名称
			  jQuery('#name').val(data.data.name);
			  //玩家id
			  jQuery('#playerId').val(data.data.playerId);
			  //筛选范围起始级别
			  jQuery('#startLevel').val(data.data.startLevel);
			  //筛选范围结束级别
			  jQuery('#endLevel').val(data.data.endLevel);
			  //是否是群发
			  if(data.data.isSendAll){
				  $('input:radio[name="optionsRadiosInline"]').get(1).checked = true;
			  }else{
				  $('input:radio[name="optionsRadiosInline"]').get(0).checked = true;
			  }
			  //筛选服务器
			  jQuery('#sendHost').val(data.data.sendHost);
		}
	});
}

/** 
 * 日期时间校验 
 * @param date 
 * @returns {Boolean} 
 */  
function isDateTime(dateTime)    
{    
    var reg = /^(\d{4})(-|\/)(\d{2})\2(\d{2}) (\d{2}):(\d{2}):(\d{2})$/;    
    var r = dateTime.match(reg);  
    if(r==null)return false;    
    var d= new Date(r[1],r[3]-1,r[4],r[5],r[6],r[7]);    
    return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);    
}

/**
 * 封装页面值和校验
 */
function setAndValidate(data){
	  //标题
	  var title = jQuery.trim(jQuery('#title').val());
	  //内容
	  var content = jQuery.trim(jQuery('#content').val());
	  //附件
	  var resList = getResValue();
	  //名称
	  var name = jQuery.trim(jQuery('#name').val());
	  //玩家id
	  var playerId = jQuery.trim(jQuery('#playerId').val());
	  //筛选范围起始级别
	  var startLevel = jQuery.trim(jQuery('#startLevel').val());
	  //筛选范围结束级别
	  var endLevel = jQuery.trim(jQuery('#endLevel').val());
	  //是否是群发
	  var isSendAll = $('input:radio[name="optionsRadiosInline"]:checked').val();
	  //筛选服务器
	  var sendHost = jQuery('#sendHost').val();
	  var mailId = jQuery("#mailList").val();
	  if(mailId.length > 0 ){
		  data.id = mailId;
	  }
	  data.title = title;
	  data.content = content;
	  data.resList = resList;
	  data.name = name;
	  data.isSendAll = isSendAll;
	  data.sendHost = sendHost;
	  if(title.length == 0 || name.length == 0){
		  jQuery('#modalText').text('邮件标题或邮件名称不能为空!');
		  jQuery('#myModal').modal('show');
		  return false;
	  }
	  if(title.length > 40){
		  jQuery('#modalText').text('标题不能超过40');
		  jQuery('#myModal').modal('show');
		  return false;
	  }
	  if(content.length > 500){
		  jQuery('#modalText').text('内容不能超过500');
		  jQuery('#myModal').modal('show');
		  return false;
	  }
	  if(name.length > 15){
		  jQuery('#modalText').text('邮件名称不能超过15');
		  jQuery('#myModal').modal('show');
		  return false;
	  }
	  //判断是否是群发
	  if(isSendAll=='true'){
		  var regLevel = /^[0-9]\d*$/;
		  if(startLevel.length > 0){
			  //校验正则
				if(!regLevel.test(startLevel)){
					jQuery('#modalText').text('起始级别只能为0和正整数');
					jQuery('#myModal').modal('show');
					return false;
				}
				data.startLevel = startLevel;
		  }
		  if(endLevel.length > 0){
			  //校验正则
				if(!regLevel.test(endLevel)){
					jQuery('#modalText').text('结束级别只能为0和正整数');
					jQuery('#myModal').modal('show');
					return false;
				}
				data.endLevel = endLevel;
		  }
		  if(startLevel.length > 0 && endLevel.length > 0){
			  if(endLevel < startLevel){
				jQuery('#modalText').text('结束级别只能比起始级别大');
				jQuery('#myModal').modal('show');
				return false;
			  }
		  }	  
	  }else{
		  //单发
		if(playerId.length == 0){
			jQuery('#modalText').text('玩家id不能为空');
			  jQuery('#myModal').modal('show');
			  return false;
		}
		  //校验正则
		var regId = /^[1-9]\d*$/;
		if(!regId.test(playerId)){
			jQuery('#modalText').text('玩家id只能为正整数');
			jQuery('#myModal').modal('show');
			return false;
		}
		data.playerId = playerId;
	  }
	  return true;
}
