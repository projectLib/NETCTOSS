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
            //删除
            function delete_service(id) {
                var r = window.confirm("确定要删除此业务账号吗？");
                //发起异步请求，实现开通
            	//post函数的3个参数，1是url，2是本次请求携带的参数，3是执行成功后的回调函数
            	if(r){
	                $.post(
	                	"deleteService.do",
	                	{"service_id":id},
	            		function(data) {
            			//设置提示信息
            			$("#operate_msg").text(data.message);
            			//显示出提示信息
            			$("#operate_result_info").show();
            			//延迟2s刷新
           				setTimeout(function(){
            					location.href = "serviceList.do";
            			}, 2000);
            		}
            	);
                }
            }
            
            //开通业务
            function start_service(id) {
                var r = window.confirm("确定要开通此业务账号吗？");
                //发起异步请求，实现开通
            	//post函数的3个参数，1是url，2是本次请求携带的参数，3是执行成功后的回调函数
            	if(r){
	                $.post(
	                	"updateByStatusToStartService.do",
	                	{"service_id":id},
	            		function(data) {
            			//设置提示信息
            			$("#operate_msg").text(data.message);
            			//显示出提示信息
            			$("#operate_result_info").show();
            			//延迟2s刷新
           				setTimeout(function(){
	            			//如果开通成功，刷新页面
	            			if(data.success) {
            					//刷新当前页
            					location.href = "serviceList.do";
	            			} else {
	            				$("#operate_result_info").hide();
	            			}
            			}, 2000);
            		}
            	);
                }
            }
            //暂停
            function pause_service(id) {
                var r = window.confirm("确定要暂停此业务账号吗？");
                //发起异步请求，实现开通
            	//post函数的3个参数，1是url，2是本次请求携带的参数，3是执行成功后的回调函数
                if(r){
	                $.post(
	                	"updateByStatusToPauseService.do",
	                	{"service_id":id},
	            		function(data) {
            			//设置提示信息
            			$("#operate_msg").text(data.message);
            			//显示出提示信息
            			$("#operate_result_info").show();
            			//延迟2s刷新
           				setTimeout(function(){
	            			//如果开通成功，刷新页面
            				location.href = "serviceList.do";
            			}, 2000);
            		}
            	);
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
            <form action="serviceList.do" method="post">
                <!--查询-->
                <div class="search_add">                        
                    <div>OS 账号：<input name="osUserName" type="text" value="${servicePage.osUserName}" class="width100 text_search" /></div>                            
                    <div>服务器 IP：<input name="unixHost" type="text" value="${servicePage.unixHost}" class="width100 text_search" /></div>
                    <div>身份证：<input name="idcardNo" type="text"  value="${servicePage.idcardNo}" class="text_search" /></div>
                    <div>状态：
                        <select name="status" class="select_search">
                            <option value="-1" <c:if test="${servicePage.status==-1}">selected</c:if>>全部</option>
                            <option value="0" <c:if test="${servicePage.status==0}">selected</c:if>>开通</option>
                            <option value="1" <c:if test="${servicePage.status==1}">selected</c:if>>暂停</option>
                            <option value="2" <c:if test="${servicePage.status==2}">selected</c:if>>删除</option>
                        </select>
                    </div>
                    <div><input type="submit" value="搜索" class="btn_search" /></div>
                    <input type="button" value="增加" class="btn_add" onclick="location.href='toAddService.do';" />
                </div> 
            </form> 
                 <!--操作提示-->
                <div id="operate_result_info" class="operate_success">
                    <img src="../images/close.png" onclick="this.parentNode.style.display='none';" />
                    <span id="operate_msg">删除成功！</span>
                </div>   
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                    <tr>
                        <th class="width50">业务ID</th>
                        <th class="width70">账务账号ID</th>
                        <th class="width150">身份证</th>
                        <th class="width70">姓名</th>
                        <th>OS 账号</th>
                        <th class="width50">状态</th>
                        <th class="width100">服务器 IP</th>
                        <th class="width100">资费</th>                                                        
                        <th class="width200"></th>
                    </tr>
                <c:forEach items="${services}" var="s">
                	<tr>
                        <td><a href="toFindById.do?service_id=${s.SERVICE_ID}" title="查看明细">${s.SERVICE_ID}</a></td>
                        <td>${s.ACCOUNT_ID}</td>
                        <td>${s.IDCARD_NO}</td>
                        <td>${s.REAL_NAME}</td>
                        <td>${s.OS_USERNAME}</td>
                        <td>
                        	<c:if test="${s.STATUS==0}">
                        		开通
                        	</c:if>
                        	<c:if test="${s.STATUS==1}">
                        		暂停
                        	</c:if>
                        	<c:if test="${s.STATUS==2}">
                        		删除
                        	</c:if>
                        </td>
                        <td>${s.UNIX_HOST}</td>
                        <td>
                            <a class="summary"  onmouseover="showDetail(true,this);" onmouseout="showDetail(false,this);">${s.NAME}</a>
                            <!--浮动的详细信息-->
                            <div class="detail_info">
                                ${s.DESCR}
                            </div>
                        </td>                            
                        <td class="td_modi">
                        	<c:if test="${s.STATUS==0}">
	                            <input type="button" value="暂停" class="btn_pause" onclick="pause_service(${s.SERVICE_ID});" />
	                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?service_id=${s.SERVICE_ID}';" />
	                            <input type="button" value="删除" class="btn_delete" onclick="delete_service(${s.SERVICE_ID});" />
                        	</c:if>
                        	<c:if test="${s.STATUS==1}">
                        		<input type="button" value="开通" class="btn_start" onclick="start_service(${s.SERVICE_ID});" />
	                            <input type="button" value="修改" class="btn_modify" onclick="location.href='toUpdateService.do?service_id=${s.SERVICE_ID}';" />
	                            <input type="button" value="删除" class="btn_delete" onclick="delete_service(${s.SERVICE_ID});" />
                        	</c:if>
                        </td>
                    </tr>
                </c:forEach>    
                </table>                
                <p>业务说明：<br />
                1、创建即开通，记载创建时间；<br />
                2、暂停后，记载暂停时间；<br />
                3、重新开通后，删除暂停时间；<br />
                4、删除后，记载删除时间，标示为删除，不能再开通、修改、删除；<br />
                5、业务账号不设计修改密码功能，由用户自服务功能实现；<br />
                6、暂停和删除状态的账务账号下属的业务账号不能被开通。</p>
                </div>                    
               <!--分页-->
                <div id="pages">
                	<c:choose>
                		<c:when test="${servicePage.currentPage==1}">
                			<a href="#">首页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="serviceList.do?currentPage=1">首页</a>
                		</c:otherwise>
                	</c:choose>
                    <c:choose>
                		<c:when test="${servicePage.currentPage==1}">
                			 <a href="#">上一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="serviceList.do?currentPage=${servicePage.currentPage-1}">上一页</a>
                		</c:otherwise>
                	</c:choose>
        	        <c:forEach begin="1" end="${servicePage.totalPage}" var="p">
        	        	<c:choose>
	        	        	<c:when test="${servicePage.currentPage==p}">
	                			<a href="serviceList.do?currentPage=${p}" class="current_page">${p}</a>
	                		</c:when>
	                		<c:otherwise>
	                			<a href="serviceList.do?currentPage=${p}" >${p}</a>
	                		</c:otherwise>
                		</c:choose>
        	        </c:forEach>
                    <c:choose>
                		<c:when test="${servicePage.currentPage==servicePage.totalPage}">
                			<a href="#">下一页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="serviceList.do?currentPage=${servicePage.currentPage+1}">下一页</a>
                		</c:otherwise>
                	</c:choose>
                    <c:choose>
                		<c:when test="${servicePage.currentPage==servicePage.totalPage}">
                			<a href="#">尾页</a>
                		</c:when>
                		<c:otherwise>
                			<a href="serviceList.do?currentPage=${servicePage.totalPage}">尾页</a>
                		</c:otherwise>
                	</c:choose>
                </div>                    
        </div>
        <!--主要区域结束-->
        <div id="footer">
            <p>[源自北美的技术，最优秀的师资，最真实的企业环境，最适用的实战项目]</p>
            <p>版权所有(C)加拿大达内IT培训集团公司 </p>
        </div>
    </body>
</html>
