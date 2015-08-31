<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" />
        <script type="text/javascript" src="../js/jquery-1.11.1.js"></script>
        <script language="javascript" type="text/javascript">
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
        	//验证密码
        	function checkPasswd(){
        		$("#passwd_msg1").text("6-30长度以内的字母、数字和下划线的组合").removeClass("error_msg");
        		$("#passwd_msg2").text("两次密码不同").removeClass("error_msg");
        		var passwd1 = $("#login_passwd1").val();
        		var passwd2 = $("#login_passwd2").val();
        		var reg = /^\w{6,30}$/;
        		if(!reg.test(passwd1)){
        			$("#passwd_msg1").text("6-30长度以内的字母、数字和下划线的组合").addClass("error_msg");
        			return;
        		}
        		$("#passwd_msg1").text("").removeClass("error_msg");
        		if(passwd1==passwd2){
        			$("#passwd_msg2").text("密码有效").removeClass("error_msg");
        		}else{
        			$("#passwd_msg2").text("两次密码不同").addClass("error_msg");
        		}
        	}
       		
            //保存成功的提示信息
            function showResult() {
                showResultDiv(true);
                window.setTimeout("showResultDiv(false);", 3000);
            }
            function showResultDiv(flag) {
                var divResult = document.getElementById("save_result_info");
                if (flag)
                    divResult.style.display = "block";
                else
                    divResult.style.display = "none";
            }

            //显示选填的信息项
            function showOptionalInfo(imgObj) {
                var div = document.getElementById("optionalInfo");
                if (div.className == "hide") {
                    div.className = "show";
                    imgObj.src = "../images/hide.png";
                }
                else {
                    div.className = "hide";
                    imgObj.src = "../images/show.png";
                }
            }
        </script>
    </head>
    <body>
        <!--Logo区域开始-->
        <div id="header">
            <jsp:include page="/WEB-INF/base/logo.jsp"/>             
        </div>
        <!--Logo区域结束-->
        <!--导航区域开始-->
        <div id="navi">
        	<jsp:include page="/WEB-INF/base/menu.jsp"/>
        </div>
        <!--导航区域结束-->
        <!--主要区域开始-->
        <div id="main">       
            <!--保存成功或者失败的提示消息-->     
            <div id="save_result_info" class="save_fail">保存失败，该身份证已经开通过账务账号！</div>
            <form action="accountAdd.do" method="post" class="main_form">
                <!--必填项-->
                <div class="text_info clearfix"><span>姓名：</span></div>
                <div class="input_info">
                    <input name="real_name" type="text" value="" />
                    <span class="required">*</span>
                    <div  class="validate_msg_long" >6-30长度以内的汉字、字母和数字的组合</div>
                </div>
                <div class="text_info clearfix"><span>身份证：</span></div>
                <div class="input_info">
                    <input name="idcard_no" id="idcard_no" onblur="check_idcard_no();" type="text" value="" />
                    <span class="required">*</span>
                    <div id="idcard_no_msg" class="validate_msg_long">正确的身份证号码格式</div>
                </div>
                <div class="text_info clearfix"><span>登录账号：</span></div>
                <div class="input_info">
                    <input name="login_name" type="text" value=""  />
                    <span class="required">*</span>
                    <div class="validate_msg_long">30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>密码：</span></div>
                <div class="input_info">
                    <input id="login_passwd1" type="password" onblur="checkPasswd();"  />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="passwd_msg1">6-30长度以内的字母、数字和下划线的组合</div>
                </div>
                <div class="text_info clearfix"><span>重复密码：</span></div>
                <div class="input_info">
                    <input name="login_passwd" id="login_passwd2" type="password" onblur="checkPasswd();" />
                    <span class="required">*</span>
                    <div class="validate_msg_long" id="passwd_msg2">两次密码必须相同</div>
                </div>     
                <div class="text_info clearfix"><span>电话：</span></div>
                <div class="input_info">
                    <input name="telephone" type="text" class="width200"/>
                    <span class="required">*</span>
                    <div class="validate_msg_medium">正确的电话号码格式：手机或固话</div>
                </div>                
                <!--可选项-->
                <div class="text_info clearfix"><span>可选项：</span></div>
                <div class="input_info">
                    <img src="../images/show.png" alt="展开" onclick="showOptionalInfo(this);" />
                </div>
                <div id="optionalInfo" class="hide">
                    <div class="text_info clearfix"><span>推荐人身份证号码：</span></div>
                    <div class="input_info">
                    	<select name="recommender_id">
                    		<option value="" selected>无推荐人</option>
                    		<c:forEach items="${accounts}" var="a">
                    			<option value="${a.account_id}">${a.idcard_no}</option>
                    		</c:forEach>
                    	</select>
                        <div class="validate_msg_long">如果有推荐人，请选择正确的身份证号码，默认无</div>
                    </div>
                    <div class="text_info clearfix"><span>生日：</span></div>
                    <div class="input_info">
                        <input name="birthdate" id="birthdate" type="text" value="" readonly class="readonly" />
                    </div>
                    <div class="text_info clearfix"><span>Email：</span></div>
                    <div class="input_info">
                        <input name="email" type="text" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内，合法的 Email 格式</div>
                    </div> 
                    <div class="text_info clearfix"><span>职业：</span></div>
                    <div class="input_info">
                        <select name="occupation">
                            <option value="1">干部</option>
                            <option value="2">学生</option>
                            <option value="3">技术人员</option>
                            <option value="4">其他</option>
                        </select>                        
                    </div>
                    <div class="text_info clearfix"><span>性别：</span></div>
                    <div class="input_info fee_type">
                        <input type="radio" name="gender" value="f" checked="checked" id="female" />
                        <label for="female">女</label>
                        <input type="radio" name="gender" value="m" id="male" />
                        <label for="male">男</label>
                    </div> 
                    <div class="text_info clearfix"><span>通信地址：</span></div>
                    <div class="input_info">
                        <input name="mailaddress"type="text" class="width350"/>
                        <div class="validate_msg_tiny">50长度以内</div>
                    </div> 
                    <div class="text_info clearfix"><span>邮编：</span></div>
                    <div class="input_info">
                        <input name="zipcode" type="text"/>
                        <div class="validate_msg_long">6位数字</div>
                    </div> 
                    <div class="text_info clearfix"><span>QQ：</span></div>
                    <div class="input_info">
                        <input name="qq" type="text"/>
                        <div class="validate_msg_long">5到13位数字</div>
                    </div>                
                </div>
                <!--操作按钮-->
                <div class="button_info clearfix">
                    <input type="submit" value="保存" class="btn_save" />
                    <input type="button" value="取消" class="btn_save" />
                </div>
            </form>  
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <span>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</span>
            <br />
            <span>版权所有(C)加拿大达内IT培训集团公司 </span>
        </div>
    </body>
</html>
