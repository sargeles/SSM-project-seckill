<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@include file="common/tag.jsp" %>

<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <title>秒杀列表页</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 新 Bootstrap 核心 CSS 文件 -->
	<link rel="stylesheet"
		href="<%=basePath%>/libs/bootstrap/css/bootstrap.min.css">
	<!--新 Bootstrap时间选择器的 CSS 文件-->
	<link
		href="<%=basePath%>/libs/bootstrap/css/bootstrap-datetimepicker.min.css"
		rel="stylesheet" media="screen">

<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
<script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->
  </head>
  <body>
    <!-- 页面显示部分 -->
    <div class="container">
    	<div class="panel panel-default">
    		<div class="panel-heading text-center">
    			<h2>秒杀列表</h2>
    		</div>
    		<div class="panel-body">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>名称</th>
							<th>库存</th>
							<th>开始时间</th>
							<th>结束时间</th>
							<th>创建时间</th>
							<th>详情页</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="seckill" items="${list}">
                            <tr>
                                <td>${seckill.name}</td>
                                <td>${seckill.number}</td>
                                <td><fmt:formatDate value="${seckill.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${seckill.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><fmt:formatDate value="${seckill.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                <td><a class="btn btn-info" href="/seckill/seckill/${seckill.seckillId}/detail">秒杀</a></td>
                            </tr>
                        </c:forEach>
					</tbody>
				</table>
    		</div>
    	</div>
    </div>
  </body>
  

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="UTF-8"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</html>