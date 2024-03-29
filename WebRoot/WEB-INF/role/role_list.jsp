﻿<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>达内－NetCTOSS</title>
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global.css" />
        <link type="text/css" rel="stylesheet" media="all" href="../styles/global_color.css" /> 
        <script language="javascript" type="text/javascript">
            function deleteRole() {
                var r = window.confirm("确定要删除此角色吗？");
                document.getElementById("operate_result_info").style.display = "block";
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
            <form action="roleList.do" method="post">
                <!--查询-->
                <div class="search_add">
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddRole.do';" />
                </div>  
                <!--删除的操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    删除成功！
                </div> <!--删除错误！该角色被使用，不能删除。-->
                <!--数据区域：用表格展示数据-->     
                <div id="data">                      
                    <table id="datalist">
                        <tr>                            
                            <th>角色 ID</th>
                            <th>角色名称</th>
                            <th class="width600">拥有的权限</th>
                            <th class="td_modi"></th>
                        </tr>
                        <c:forEach items="${roles}" var="r">
                        	<tr>
		                        <td>${r.role_id}</td>
		                        <td>${r.name}</td>
		                        <td>
			                        <c:forEach items="${r.modules}" var="m" varStatus="s">
			                        	<c:choose>
				                        	<c:when test="${s.last}">
				                        		${m.name}
				                        	</c:when>
				                        	<c:otherwise>
				                        		${m.name}、
				                        	</c:otherwise>
			                        	</c:choose>
			                        </c:forEach>
		                        </td>
		                        <td>
                                <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateRole.do?role_id=${r.role_id}';"/>
                                <input type="button" value="删除" class="btn_delete" onclick="location.href='deleteRole.do?role_id=${r.role_id}';" />
                            	</td> 
                        	</tr>
	                  	</c:forEach>                     
                    </table>
                </div> 
                <!--分页-->
                <div id="pages">
                	<c:choose>
                		<c:when test="${rolePage.currentPage==1}">
                			<a href="#">首页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="roleList.do?currentPage=1">首页</a>
                		</c:otherwise>
                	</c:choose>
                    <c:choose>
                		<c:when test="${rolePage.currentPage==1}">
                			 <a href="#">上一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="roleList.do?currentPage=${rolePage.currentPage-1}">上一页</a>
                		</c:otherwise>
                	</c:choose>
        	        <c:forEach begin="1" end="${rolePage.totalPage}" var="p">
        	        	<c:choose>
	        	        	<c:when test="${rolePage.currentPage==p}">
	                			<a href="roleList.do?currentPage=${p}" class="current_page">${p}</a>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="roleList.do?currentPage=${p}" >${p}</a>
	                		</c:otherwise>
                		</c:choose>
        	        </c:forEach>
                    <c:choose>
                		<c:when test="${rolePage.currentPage==rolePage.totalPage}">
                			<a href="#">下一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="roleList.do?currentPage=${rolePage.currentPage+1}">下一页</a>
                		</c:otherwise>
                	</c:choose>
                    <c:choose>
                		<c:when test="${rolePage.currentPage==rolePage.totalPage}">
                			<a href="#">尾页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="roleList.do?currentPage=${rolePage.totalPage}">尾页</a>
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
