<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script type="text/javascript">
        	//登陆验证
        	function check_login(){
        		var reg = /^\w{1,20}$/;
        		var admin_code = $("#admin_code").val();
        		var password = $("#password").val();
        		var userCode = $("#userCode").val();
        		if(admin_code==""){
        			$("#code_msg").text("用户名不能为空").addClass("error_msg");
        			return;
        		}else if(!reg.test(admin_code)){
        			$("#code_msg").text("用户名格式不正确,请输入1-30位的字母、数字、下划线").addClass("error_msg");
        			return;
        		}else if(password==""){
        			$("#password_msg").text("密码不能为空").addClass("error_msg");
        			return;
        		}else if(!reg.test(admin_code)){
        			$("#password_msg").text("密码格式不正确,请输入1-30位的字母、数字、下划线").addClass("error_msg");
        			return;
        		}else if(userCode==""){
        			$("#image_code_msg").text("验证码不能为空").addClass("error_msg");
        			return;
        		}
        		$.post(
        			"checkLogin.do",
        			{"admin_code":admin_code,"password":password,"userCode":userCode},
        			function(data){
        				if(data==0) {
    						//校验通过
    						location.href = "toIndex.do";
    					} else if(data==1) {
    						//账号错误
    						$("#code_msg").text("账号不存在.");
    					} else if(data==2) {
    						//密码错误
    						$("#password_msg").text("密码错误.");
    					} else if(data==3) {
    						//密码错误
    						$("#image_code_msg").text("验证码错误.");
    					}
        			}
        		);
        	}
        	//清楚错误消息
        	function clear_msg(id){
        		$("#"+id).text("");
        	}
        </script>
    </head>
    <body class="index">
        <div class="login_box">
            <table>
                <tr>
                    <td class="login_info">账号：</td>
                    <td colspan="2"><input id="admin_code" type="text" class="width150" onfocus="clear_msg('code_msg');"/></td>
                    <td class="login_error_info"><span class="required" id="code_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">密码：</td>
                    <td colspan="2"><input id="password" type="password" class="width150" onfocus="clear_msg('password_msg');"/></td>
                    <td><span class="required" id="password_msg"></span></td>
                </tr>
                <tr>
                    <td class="login_info">验证码：</td>
                    <td class="width70"><input name=""  id="userCode" type="text" class="width70" onfocus="clear_msg('password_msg');" /></td>
                    <td><img src="createImage.do" alt="验证码" title="点击更换" onclick="this.src='createImage.do?date='+new Date().getTime()"/></td>  
                    <td><span class="required" id="image_code_msg"></span></td>              
                </tr>            
                <tr>
                    <td></td>
                    <td class="login_button" colspan="2">
                        <a href="javascript:check_login();"><img src="../images/login_btn.png" /></a>
                    </td>    
                    <td><span class="required" id="login_msg"></span></td>                
                </tr>
            </table>
        </div>
    </body>
</html>
