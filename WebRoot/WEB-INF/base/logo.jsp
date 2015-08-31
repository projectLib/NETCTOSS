<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	function clearSession(){
		var r = window.confirm("你确定要退出系统吗?");
		if(r){
			location.href = "../main/exit.do";
		}
	}
</script>
<img src="../images/logo.png" alt="logo" class="left"/>
            <a href="javascript:clearSession();">[退出]</a>