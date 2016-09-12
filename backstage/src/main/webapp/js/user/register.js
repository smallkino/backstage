var localObj = window.location;
var contextPath = localObj.pathname.split("/")[1];
jQuery('#submit').click(function(){
	//校验用户名与密码
	var username = jQuery.trim(jQuery('#username').val());
	var password = jQuery.trim(jQuery('#password').val());
	var confirmPassword = jQuery.trim(jQuery('#confirmPassword').val());
	//为空
	if(username.length == 0 || password.length == 0 || confirmPassword.length == 0){
		jQuery('#modalText').text('用户名或密码不能为空!');
		jQuery('#myModal').modal('show');
		return;
	}
	//确认密码输入一致
	if(password != confirmPassword){
		jQuery('#modalText').text('两次密码输入不一致!');
		jQuery('#myModal').modal('show');
		return;
	}
	//校验长度
	if(username.length < 5 || username.length > 10 || password.length < 5 || password.length > 10){
		jQuery('#modalText').text('用户名或密码长度不能小于5大于10!');
		jQuery('#myModal').modal('show');
		return;
	}
	//校验正则
	var reg = /^[0-9a-zA-Z_]*$/;
	if(!reg.test(username)){
		jQuery('#modalText').text('用户名只能为数字或字母!');
		jQuery('#myModal').modal('show');
		return;
	}
	if(!reg.test(password)){
		jQuery('#modalText').text('密码只能为数字或字母!');
		jQuery('#myModal').modal('show');
		return;
	}
	
	jQuery.ajax({
			url:'/'+contextPath+'/user/registerSubmit',
			dataType:'json',
			data:{'username':username,'password':password},
			method:"POST",
			success:function(data){
				//注册成功需要关闭后跳转
				if(data.data){
					jQuery('#myModal').on('hide.bs.modal',function(){
						window.location.href='/'+contextPath+'/user/login.html';
					})
				}
				jQuery('#modalText').text(data.message);
				jQuery('#myModal').modal('show');
			}
	});
});