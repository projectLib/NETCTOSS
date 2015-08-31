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
            <form action="" method="" class="main_form">
                <div class="text_info clearfix"><span>资费ID：</span></div>
                <div class="input_info"><input name="cost_id" type="text" class="readonly" readonly value="${cost.cost_id}" /></div>
                <div class="text_info clearfix"><span>资费名称：</span></div>
                <div class="input_info"><input name="name" type="text" class="readonly" readonly value="${cost.name}"/></div>
                <div class="text_info clearfix"><span>资费状态：</span></div>
                <div class="input_info">
                    <select name="status" class="readonly" disabled>
                        <option <c:if test="${cost.cost_type==0}">selected</c:if> >开通</option>
                        <option <c:if test="${cost.cost_type!=1}">selected</c:if> >暂停</option>
                        <option>删除</option>
                    </select>                        
                </div>
                <div class="text_info clearfix"><span>资费类型：</span></div>
                <div class="input_info fee_type">
                    <input type="radio" name="cost_type" <c:if test="${cost.cost_type==1}">checked</c:if> id="monthly" disabled="disabled" />
                    <label for="monthly">包月</label>
                    <input type="radio" name="cost_type" <c:if test="${cost.cost_type==2}">checked</c:if> id="package" disabled="disabled" />
                    <label for="package">套餐</label>
                    <input type="radio" name="cost_type" <c:if test="${cost.cost_type==3}">checked</c:if> id="timeBased" disabled="disabled" />
                    <label for="timeBased">计时</label>
                </div>
                <div class="text_info clearfix"><span>基本时长：</span></div>
                <div class="input_info">
                    <input name="base_duration" type="text" class="readonly" readonly value="${cost.base_duration}"  />
                    <span>小时</span>
                </div>
                <div class="text_info clearfix"><span>基本费用：</span></div>
                <div class="input_info">
                    <input name="base_cost" type="text"  class="readonly" readonly value="${cost.base_cost}" />
                    <span>元</span>
                </div>
                <div class="text_info clearfix"><span>单位费用：</span></div>
                <div class="input_info">
                    <input name="unit_cost" type="text"  class="readonly" readonly value="${cost.unit_cost}" />
                    <span>元/小时</span>
                </div>
                <div class="text_info clearfix"><span>创建时间：</span></div>
                <div class="input_info"><input name="creatime" type="text"  class="readonly" readonly value="${cost.creatime}" /></div>      
                <div class="text_info clearfix"><span>启动时间：</span></div>
                <div class="input_info"><input name="startime"type="text"  class="readonly" readonly value="${cost.startime}" /></div>      
                <div class="text_info clearfix"><span>资费说明：</span></div>
                <div class="input_info_high">
                    <textarea name="descr" class="width300 height70 readonly" readonly>${cost.descr}</textarea>
                </div>                    
                <div class="button_info clearfix">
                    <input type="button" value="返回" class="btn_save" onclick="history.back();" />
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
