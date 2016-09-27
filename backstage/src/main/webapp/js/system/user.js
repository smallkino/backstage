var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
jQuery(function(){
	
	//确定提交新增菜单
	jQuery('#confirmAdd').click(function(){
		jQuery('#addModal').modal('hide');
		var data={};
		var id = jQuery.trim(jQuery('#id').val());
		var roleId = jQuery("#roleList").find("option:selected").val();
		if(id && id.length > 0){
			data.id=id;
		}
		if(roleId && roleId.length > 0){
			data.roleId = roleId
		}else{
			data.roleId = "-1";
		}
		jQuery.ajax({
			   type: "post", // 或者 "get"
		       url: "/"+contextPath+"/user/saveOrUpdate",
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
	
	 //表格初始化
	 $("#table_local").dataTable({
         "processing": true,
	      "serverSide": true,
	      "searching" : false,
	      "ordering": false,
	      "type":"post",
	      "ajax": {
	         url:"/"+contextPath+"/user/getAllUser",
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
	         {"data":"username"},
	         {"data":"id"}
	       ],
	      "columnDefs": [
	         {
	              "render": function ( data, type, row ) {
	                  return '<button name="updateButton" currentId='+data+'  type="button" class="btn btn-info btn-sm">分配权限</button>';  
	              },
	              "targets": 1 }       
	       ]
	   });
	 
	 //渲染数据完注册事件
	 jQuery("#table_local").on('draw.dt', function () {
		 //点击修改
		 jQuery('[name="updateButton"]').click(function(){
			 var id = jQuery(this).attr('currentId');
			 jQuery.ajax({
				   type: "post", // 或者 "get"
			       url: "/"+contextPath+"/user/getById",
			       data:{"id":id},
			       success: function(resp) {
			    	   if(resp.success){
			    		   jQuery('#name').attr('placeholder',resp.data.username);
			    		   jQuery('#id').val(resp.data.id);
			    		   if(resp.data.roleId && resp.data.roleId.length > 0){
			    			   jQuery('#roleList').val(resp.data.roleId);
			    		   }
			    		   jQuery('#addModal').modal('show');
			    	   }else{
			    		   jQuery('#modalText').text(resp.message);
			    		   jQuery('#myModal').modal('show');
			    	   }
			       }
			  });
		 });
	 });
	
});

