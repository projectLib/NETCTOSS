﻿<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
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
    <body onload="initialYearAndMonth();">
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
            <form action="" method="">
                <!--查询-->
                <div class="search_add">                        
                    <div>账务账号：<span class="readonly width70">admin1</span></div>                            
                    <div>OS 账号：<span class="readonly width100">openlab1</span></div>
                    <div>服务器 IP：<span class="readonly width100">192.168.0.20</span></div>
                    <div>计费时间：<span class="readonly width70">2013年8月</span></div>
                    <div>费用：<span class="readonly width70">34.78</span></div>
                    <input type="button" value="返回" class="btn_add" onclick="location.href='bill_item.html';" />
                </div>  
                <!--数据区域：用表格展示数据-->     
                <div id="data">            
                    <table id="datalist">
                        <tr>
                            <th class="width150">客户登录 IP</th>
                            <th class="width150">登入时刻</th>
                            <th class="width150">登出时刻</th>
                            <th class="width100">时长（秒）</th>
                            <th class="width150">费用</th>
                            <th>资费</th>
                        </tr>
                        <tr>
                            <td>192.168.100.100</td>
                            <td>2013/01/01 12:12:12</td>
                            <td>2013/01/01 12:12:22</td>
                            <td>10</td>
                            <td>0</td>
                            <td>包 20 小时</td>
                        </tr>
                        <tr>
                            <td>192.168.100.100</td>
                            <td>2013/01/01 12:12:12</td>
                            <td>2013/01/01 12:12:22</td>
                            <td>10</td>
                            <td>0</td>
                            <td>包 20 小时</td>
                        </tr>
                        <tr>
                            <td>192.168.100.100</td>
                            <td>2013/01/01 12:12:12</td>
                            <td>2013/01/01 12:12:22</td>
                            <td>10</td>
                            <td>0</td>
                            <td>包 20 小时</td>
                        </tr>
                        <tr>
                            <td>192.168.100.100</td>
                            <td>2013/01/01 12:12:12</td>
                            <td>2013/01/01 12:12:22</td>
                            <td>10</td>
                            <td>0.45</td>
                            <td>包 20 小时</td>
                        </tr>
                    </table>
                </div>
                <!--分页-->
                <div id="pages">
        	        <a href="#">上一页</a>
                    <a href="#" class="current_page">1</a>
                    <a href="#">2</a>
                    <a href="#">3</a>
                    <a href="#">4</a>
                    <a href="#">5</a>
                    <a href="#">下一页</a>
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
