//检测name
var nameResult;
var rName ;
window.onload = function(){
	/*
	 * 当整个页面加载完成之后（dom数已经生成完毕）会执行这儿的代码。
	 * @memberOf {TypeName} 
	 */
	rName = $("#name").val();
};
function check_name(){
	alert(1);
	nameResult = null;
	var name = $("#name").val();
	// 检查是否修改了name
	if(name==rName){
		//如果没有修改name，还是从数据库中查到的name
		$("#name_msg").text("角色名有效.").removeClass("error_msg");
		nameResult = true;
		return;
	}
	//校验名称格式
	var reg = /^[\u4E00-\u9FA5A-Za-z0-9]{1,20}$/;
	if(!reg.test(name)){
		$("#name_msg").text("1-20长度的字母、数字和汉字的组合").addClass("error_msg");
		nameResult = false;
		return;
	}
	$.post(
		"checkName.do",
		{"name":name},
		function(date){
			if(date){
				//如果校验通过，给予正确提示
				$("#name_msg").text("角色名有效.").removeClass("error_msg");
				nameResult = true;
			}else{
				//如果校验失败，给予错误提示
				$("#name_msg").text("角色名已存在.").addClass("error_msg");
				nameResult = false;
			}
		}
	);
}
//校验是否选中模块
function check_module(){
	alert(1);
	var checkObjs = $(":checkbox[name='moduleIds']:checked");
	if(checkObjs.length==0) {
		$("#module_msg").text("请至少选择一个模块.").addClass("error_msg");
		return false;
	} else {
		$("#module_msg").text("").removeClass("error_msg");
		return true;
	}
}
//检测save
function save(){
	//先调用非异步请求的校验方法
	if(!checkModule()){
		return;
	}
	//再调用异步请求的校验方法
	checkName();
	//每隔100ms执行一次function
	var timer = setInterval(function(){
		//判断nameResult，看check_name是否执行完毕
		if(nameResult!=null){
			//如果标志非空，则说明check_name已经
			//执行完毕，结束循环
			clearInterval(timer);
			if(nameResult){
				//校验通过，提交表单
				document.forms[0].submit();
			}
			//校验失败，不提交表单
		}
		//如果标志为空，说明check_name没结束，
		//不做任何处理，继续等待下次判断
	},100);
}
//验证密码
function check_password(){
	$("#password_msg1").text("6-30长度以内的字母、数字和下划线的组合").removeClass("error_msg");
	$("#password_msg2").text("两次密码不同").removeClass("error_msg");
	var password1 = $("#login_password1").val();
	var passwdor2 = $("#login_password2").val();
	var reg = /^\w{6,30}$/;
	if(!reg.test(password1)){
		$("#password_msg1").text("6-30长度以内的字母、数字和下划线的组合").addClass("error_msg");
		return;
	}
	$("#password_msg1").text("").removeClass("error_msg");
	if(password1==password2){
		$("#password_msg2").text("密码有效").removeClass("error_msg");
	}else{
		$("#password_msg2").text("两次密码不同").addClass("error_msg");
	}
}
//检查身份证号
function check_idcard_no(){
   	//每次重新输入身份证都要重置生日
	$("#birthdate").val("");
   	//获取身份证号
    var idcard_no = $("#idcard_no").val();
   	//校验是否为空
    if(idcard_no==""){
   		$("#idcard_no_msg").text("身份证号不能为空").addClass("error_msg");
   		return;
   	}
   	var reg = /^[\u4E00-\u9FA5A-Za-z0-9_]{6,30}$/;
   	if(!reg.test(idcard_no)){
   		$("#idcard_no_msg").text("身份证号格式不正确").addClass("error_msg");
   		return;
   	}
   	///如果两个if都不执行，说明身份证格式正确，要把错误信息该回来
   	$("#idcard_no_msg").text("正确的身份证号码格式").removeClass("error_msg");
   	var year  = idcard_no.substring(6,10);
   	var month = idcard_no.substring(10,12);
   	var day   = idcard_no.substring(12,14);
   	var birthdate = year+"-"+month+"-"+day;
   	$("#birthdate").val(birthdate);
}
//验证电话
function check_telephone(){
	var telephone = $("#telephone").val();
	// 手机号码：^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$
 	// 电话号码("XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX)：^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$ 
	var reg1 = /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/;
	var reg2 = /^(\(\d{3,4}-)|\d{3.4}-)?\d{7,8}$/;
	if(!(reg1.test(telephone)||reg2.test(telephone))){
		$("telephone_msg").text("电话号码或手机号码格式不对").addClass("error_msg");
		return;
	}
	$("telephone_msg").text("电话号码或手机号码格式正确").removeClass("error_msg");
}
//验证邮箱
function check_email(){
	var email = $("#email").val();
	//Email地址：^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	if(!reg.test(email)){
		$("email_msg").text("邮箱格式不对").addClass("error_msg");
		return;
	}
	$("email_msg").text("邮箱格式正确").removeClass("error_msg");
}
