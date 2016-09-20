<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀详情页面</title>
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
    <div class="container">
        <div class="panel panel-default text-center">
            <div class="panel-heading text-center">
                <h2>${seckill.name}</h2>
            </div>
            <div class="panel-body">
                <h2 class="text-danger">
                    <%--显示Time图标--%>
                        <span class="glyphicon glyphicon-time"></span>
                    <%--显示倒计时--%>
                        <span class="glyphicon" id="seckill-box"></span>
                </h2>
            </div>
        </div>
    </div>

    <div class="modal fade" id="killPhoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static" data-keyboard="false">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title">
                        <span class="glphyicon glphyicon-phone"></span>秒杀电话:
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 clo-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhoneKey" placeholder="填写手机号^o^" class="form-control">
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div style="display: none;margin-bottom:20px;padding-top: 0;padding-bottom: 0;" role="alert" id="killPhoneMessage">
                        <strong>Warning!</strong> 手机号错误!
                    </div>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        submit
                    </button>
                </div>
            </div>
        </div>
    </div>

    <%--登陆弹出层，输入电话号码--%>
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="<%=basePath %>libs/jquery/jquery-2.0.0.min.js" charset="UTF-8"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="<%=basePath %>libs/bootstrap/js/bootstrap.min.js"></script>

    <%--JQuery Cokkie插件--%>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <%--JQuery 倒计时插件--%>
<script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.countdown/2.1.0/jquery.countdown.min.js"></script>
<%--开始编写交互逻辑--%>
    <script src="<%=basePath%>static/js/seckill.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(function(){
            //使用EL表达式传入参数
            seckill.deatil.init({
                seckillId:${seckill.seckillId},
                startTime:${seckill.startTime.time},//转换成毫秒的时间
                endTime:${seckill.endTime.time}//转换成毫秒的时间
            })
        });
    </script>
</body>
</html>
