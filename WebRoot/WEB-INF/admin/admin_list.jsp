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
        <script language="javascript" type="text/javascript">
            //显示角色详细信息
            function showDetail(flag, a) {
                var detailDiv = a.parentNode.getElementsByTagName("div")[0];
                if (flag) {
                    detailDiv.style.display = "block";
                }
                else
                    detailDiv.style.display = "none";
            }
            //重置密码
            function resetPwd(){
            	var checkObjs = $(":checkbox[name='check_admin']:checked");
            	if(checkObjs.length==0){
            		alert("请至少选择一个管理员！");
            		return;
            	}
                var r = window.confirm("确定要重置选择的管理员的密码吗?");
                if(!r){
                	return;
                }
                //遍历checkbox
                var ids = new Array();
                for(var i=0;i<checkObjs.length;i++){
                	//取得每一个被选择的checkbox
                	var checkObj = checkObjs.eq(i);
                	//取得checkbox的爷爷，即它所在的行
                	var trObj = checkObj.parent().parent();
                	//取得该行下，第2个孩子td
                	var tdObj = trObj.children().eq(1);
                	ids.push(tdObj.text());
                }
                //修改密码
                $.post(
                	"resetPassword.do",
                	{"ids":ids.toString()},
                	function(date){
                		alert(date.message);
                	}
                );
                
            }
            //删除
            function deleteAdmin(adminId) {
                var r = window.confirm("确定要删除此管理员吗？");
                if(r){
                	location.href = "deleteAdmin.do?admin_id="+adminId;
                }
            }
            //全选
            function selectAdmins(inputObj) {
                var inputArray = document.getElementById("datalist").getElementsByTagName("input");
                for (var i = 1; i < inputArray.length; i++) {
                    if (inputArray[i].type == "checkbox") {
                        inputArray[i].checked = inputObj.checked;
                    }
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
            <form action="adminList.do" method="post">
                <!--查询-->
                <div class="search_add">
                	<div>角色：<input name="roleName" type="text" value="${adminPage.roleName}" class="text_search width200" /></div>
                    <div>
                        	模块：
                        <select name="moduleId" id="selModules" class="select_search">
                            <option value="">全部</option>
                            <c:forEach items="${modules}" var="m">
                            	<option value="${m.module_id}"<c:if test="${m.module_id==adminPage.moduleId}">selected</c:if>>${m.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                    <input type="button" value="密码重置" class="btn_add" onclick="resetPwd();" />
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddAdmin.do';" />
                </div>
                <!--删除和密码重置的操作提示-->
                <div id="operate_result_info" class="operate_fail">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span>删除失败！数据并发错误。</span><!--密码重置失败！数据并发错误。-->
                </div> 
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="th_select_all">
                                <input type="checkbox" onclick="selectAdmins(this);" />
                                <span>全选</span>
                            </th>
                            <th>管理员ID</th>
                            <th>姓名</th>
                            <th>登录名</th>
                            <th>电话</th>
                            <th>电子邮件</th>
                            <th>授权日期</th>
                            <th class="width100">拥有角色</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${admins}" var="a">
                        	<tr>
	                        	<td><input name="check_admin" type="checkbox" /></td>
	                            <td>${a.admin_id}</td>
	                            <td>${a.name}</td>
	                            <td>${a.admin_code}</td>
	                            <td>${a.telephone}</td>
	                            <td>${a.email}</td>
	                            <td>
	                            	<fmt:formatDate value="${a.enrolldate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                            </td>
	                            <td>
	                            	<a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">
		                            	<c:forEach items="${a.roles}" var="r" varStatus="s">
		                            		<c:choose>
		                            			<c:when test="${s.first}">${r.name}</c:when>
		                            			<c:when test="${s.index==2}">...</c:when>
		                            		</c:choose>
		                            	</c:forEach>
	                            	</a>
	                                <!--浮动的详细信息-->
	                                <div class="detail_info">
	                                   <c:forEach items="${a.roles}" var="r" varStatus="s">
		                            		<c:choose>
		                            			<c:when test="${s.last}">${r.name}</c:when>
		                            			<c:otherwise >${r.name},</c:otherwise>
		                            		</c:choose>
		                            	</c:forEach>
	                                </div>
	                            </td>
	                            <td class="td_modi">
	                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateAdmin.do?admin_id=${a.admin_id}';" />
	                                <input type="button" value="删除" class="btn_delete" onclick="deleteAdmin(${a.admin_id});" />
	                            </td>
                        	</tr>
                        </c:forEach>                      
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
                	<!-- 如果当前页是第1页，不能点上一页 -->
                	<c:choose>
                		<c:when test="${adminPage.currentPage==1}">
                			<a href="#">上一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="adminList.do?currentPage=${adminPage.currentPage-1}">上一页</a>
                		</c:otherwise>
                	</c:choose>
                    
                    <!-- 循环输出页码，从1开始到totalPage结束 -->
                    <c:forEach begin="1" end="${adminPage.totalPage}" var="p">
                    	<!-- 如果是当前页有样式，否则没有样式 -->
                    	<c:choose>
                    		<c:when test="${p==adminPage.currentPage}">
                    			<a href="adminList.do?currentPage=${p}" class="current_page">${p}</a>
                    		</c:when>
                    		<c:otherwise>
                    			<a href="adminList.do?currentPage=${p}">${p}</a>
                    		</c:otherwise>
                    	</c:choose>
                    </c:forEach>
                    
                    <!-- 如果当前页是最后一页，不能点下一页 -->
                    <c:choose>
                    	<c:when test="${adminPage.currentPage==adminPage.totalPage}">
                    		<a href="#">下一页</a>
                    	</c:when>
                    	<c:otherwise>
                    		<a href="adminList.do?currentPage=${adminPage.currentPage+1}">下一页</a>
                    	</c:otherwise>
                    </c:choose>
                    
                </div>
            </form>
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
