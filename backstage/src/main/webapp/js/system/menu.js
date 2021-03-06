var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
jQuery(function(){
	
	//确定提交新增菜单
	jQuery('#confirmAdd').click(function(){
		jQuery('#addModal').modal('hide');
		var data={};
		var menuName = jQuery.trim(jQuery('#menuName').val());
		var menuUrl = jQuery.trim(jQuery('#menuUrl').val());
		var parentMenuId = jQuery("#parentMenu").find("option:selected").val();
		var menuId = jQuery("#menuId").val();
		if(menuName.length == 0){
			jQuery('#modalText').text('菜单名不能为空!');
			jQuery('#myModal').modal('show');
			return;
		}
		if(menuName.length > 10){
			jQuery('#modalText').text('菜单名长度小于等于10!');
			jQuery('#myModal').modal('show');
			return;
		}
		if(parentMenuId && parentMenuId.length > 0){
			data.parentId=parentMenuId;
		}else{
			data.parentId = "-1";
		}
		if(menuId && menuId.length > 0){
			data.id=menuId;
		}
		data.name=menuName;
		data.path=menuUrl;
		jQuery.ajax({
			   type: "post", // 或者 "get"
		       url: "/"+contextPath+"/menu/saveOrUpdate",
		       data:data,
		       success: function(data) {
		    	   if(data.success){
		    		   jQuery('#modalText').text("保存成功");
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
	
	 //点击新增按钮
	 jQuery('#addButton').click(function(){
		 jQuery('#menuName').val('');
		 jQuery('#menuUrl').val('');
		 jQuery("#parentMenu").val('');
		 jQuery('#menuId').val('');
		 jQuery('#addModal').modal('show');
	 });	
	 //表格初始化
	 $("#table_local").dataTable({
         "processing": true,
	      "serverSide": true,
	      "searching" : false,
	      "ordering": false,
	      "type":"post",
	      "ajax": {
	         url:"/"+contextPath+"/menu/getAllMenu",
	         type:"post"
	      },
	      language: {
	          lengthMenu: "显示 _MENU_ 条记录",
	          paginate: {//分页的样式内容。
	              previous: "上一页",
	              next: "下一页",
	              first: "第一页",
	              last: "最后"
	          },
	          zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
	          //下面三者构成了总体的左下角的内容。
	          info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，初始_MAX_ 条 "//左下角的信息显示，大写的词为关键字。
	      },
	      "columns":[
	         {"data":"name"},
	         {"data":"path"},
	         {"data":"parentName"},
	         {"data":"id"}
	       ],
	      "columnDefs": [
	         {
	              "render": function ( data, type, row ) {
	                  return '<button name="updateButton" currentId='+data+'  type="button" class="btn btn-info btn-sm">修改</button>&nbsp;<button name="deleteButton" currentId='+data+' type="button" class="btn btn-info btn-sm">删除</button>';  
	              },
	              "targets": 3 }       
	       ]
	   });
	 
	 //渲染数据完注册事件
	 jQuery("#table_local").on('draw.dt', function () {
		 //点击修改
		 jQuery('[name="updateButton"]').click(function(){
			 var id = jQuery(this).attr('currentId');
			 jQuery.ajax({
				   type: "post", // 或者 "get"
			       url: "/"+contextPath+"/menu/getById",
			       data:{"id":id},
			       success: function(resp) {
			    	   if(resp.success){
			    		   jQuery('#menuName').val(resp.data.name);
			    		   jQuery('#menuUrl').val(resp.data.path);
			    		   jQuery("#parentMenu").val(resp.data.parentId);
			    		   jQuery('#menuId').val(resp.data.id);
			    		   jQuery('#addModal').modal('show');
			    	   }else{
			    		   jQuery('#modalText').text(resp.message);
			    		   jQuery('#myModal').modal('show');
			    	   }
			       }
			  });
		 });
		 //点击删除
		 jQuery('[name="deleteButton"]').click(function(){
			 jQuery('#myModalConfim').modal('show');
			 var id = jQuery(this).attr('currentId');
			 jQuery('#confimSend').click(function(){
				 jQuery.ajax({
					   type: "post", // 或者 "get"
				       url: "/"+contextPath+"/menu/deleteById",
				       data:{"id":id},
				       success: function(resp) {
				    	   jQuery('#myModalConfim').modal('hide');
				    	   jQuery('#myModalConfim').on('hidden.bs.modal',function(){
			    			   window.location.reload(true);
			    		   })
				       }
				  });
			 });
		 });
	 });
	
});
function aa(){
	alert(jQuery('[name="updateButton"]').length);
}

