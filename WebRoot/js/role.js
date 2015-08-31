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
function checkName(){
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
function checkModule(){
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