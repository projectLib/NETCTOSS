<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ul id="menu">
	<!-- 每个人都可以访问首页 -->
	<c:if test="${currentModuleId==0}">
		<li><a href="${pageContext.request.contextPath }/main/toIndex.do" class="index_on"></a></li>
	</c:if>
	<c:if test="${currentModuleId!=0}">
		<li><a href="${pageContext.request.contextPath }/main/toIndex.do" class="index_off"></a></li>
	</c:if>
    <!-- 业务模块需要根据用户权限动态创建 -->
    <c:forEach items="${adminModules}" var="m"> 
    	<c:if test="${m.module_id==1}">
    		<c:if test="${currentModuleId==1}">
				<li><a href="${pageContext.request.contextPath }/role/roleList.do?currentPage=1" class="role_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=1}">
				<li><a href="${pageContext.request.contextPath }/role/roleList.do?currentPage=1" class="role_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==2}">
    		<c:if test="${currentModuleId==2}">
				<li><a href="${pageContext.request.contextPath }/admin/adminList.do?currentPage=1" class="admin_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=2}">
				<li><a href="${pageContext.request.contextPath }/admin/adminList.do?currentPage=1" class="admin_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==3}">
    		<c:if test="${currentModuleId==3}">
				<li><a href="${pageContext.request.contextPath }/cost/costList.do?currentPage=1" class="fee_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=3}">
				<li><a href="${pageContext.request.contextPath }/cost/costList.do?currentPage=1" class="fee_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==4}">
    		<c:if test="${currentModuleId==4}">
				<li><a href="${pageContext.request.contextPath }/account/accountList.do?currentPage=1" class="account_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=4}">
				<li><a href="${pageContext.request.contextPath }/account/accountList.do?currentPage=1" class="account_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==5}">
    		<c:if test="${currentModuleId==5}">
				<li><a href="${pageContext.request.contextPath }/service/serviceList.do?currentPage=1" class="service_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=5}">
				<li><a href="${pageContext.request.contextPath }/service/serviceList.do?currentPage=1" class="service_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==6}">
    		<c:if test="${currentModuleId==6}">
				<li><a href="${pageContext.request.contextPath }/bill/billList.do" class="bill_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=6}">
				<li><a href="${pageContext.request.contextPath }/bill/billList.do" class="bill_off"></a></li>
			</c:if>
    	</c:if>
    	<c:if test="${m.module_id==7}">
    		<c:if test="${currentModuleId==7}">
				<li><a href="${pageContext.request.contextPath }/report/reportList.do" class="report_on"></a></li>
			</c:if>
		    <c:if test="${currentModuleId!=7}">
				<li><a href="${pageContext.request.contextPath }/report/reportList.do" class="report_off"></a></li>
			</c:if>
    	</c:if>
    </c:forEach>
    
    <!-- 每个人都可以访问个人信息，修改密码 -->
    <c:if test="${currentModuleId==8}">
		<li><a href="${pageContext.request.contextPath }/user/userInfo.do" class="information_on"></a></li>
	</c:if>
    <c:if test="${currentModuleId!=8}">
		<li><a href="${pageContext.request.contextPath }/user/userInfo.do" class="information_off"></a></li>
	</c:if>
    <c:if test="${currentModuleId==9}">
		<li><a href="${pageContext.request.contextPath }/user/userPwd.do" class="password_on"></a></li>
	</c:if>
    <c:if test="${currentModuleId!=9}">
		<li><a href="${pageContext.request.contextPath }/user/userPwd.do" class="password_off"></a></li>
	</c:if>
    
</ul>
