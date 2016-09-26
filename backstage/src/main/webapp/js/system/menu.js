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
		 jQuery('#addModal').modal('show');
	 });	
	 $("#table_local").dataTable({
         //lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
         paging: true,//分页
         ordering: false,//是否启用排序
         searching: true,//搜索
         processing: "载入中",//处理页面数据的时候的显示
         bServerSide: true,                    //指定从服务器端获取数据    
         sAjaxSource: "/"+contextPath+"/menu/getAllMenu",             //获取数据的url (一般是action)   
         fnServerData: getAllMenu, //请求
         language: {
        	 lengthMenu: "显示 _MENU_ 条记录",
             search: '<span class="label label-success">搜索：</span>',//右上角的搜索文本，可以写html标签
             paginate: {//分页的样式内容。
                 previous: "上一页",
                 next: "下一页",
                 first: "第一页",
                 last: "最后"
             },
             processing: "载入中",//处理页面数据的时候的显示
             zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
             //下面三者构成了总体的左下角的内容。
             info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
             infoEmpty: "0条记录",//筛选为空时左下角的显示。
             infoFiltered: ""//筛选之后的左下角筛选提示，
         },
         paging: true,
         pagingType: "full_numbers"//分页样式的类型

     });
     $("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
});

/**
 * ajax获取所有菜单
 */
function getAllMenu(sSource, aoData, fnCallback ){
	jQuery.ajax( {    
        "type": "post",     
        "url": sSource,     
        "dataType": "json",    
        "data": aoData, // 以json格式传递  
        "success": function(resp) {    
            fnCallback(resp.data);   
        }    
    });    
}