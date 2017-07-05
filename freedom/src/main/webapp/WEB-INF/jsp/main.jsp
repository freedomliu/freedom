<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html>
<body>
<nav class="navbar navbar-inverse" role="navigation">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">我的主页</a>
    </div>
    <div>
        <ul class="nav navbar-nav navbar-left">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	菜单
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">功能1</a></li>
                    <li><a href="#">功能1</a></li>
                    <li><a href="#">功能1</a></li>
                    <li class="divider"></li>
                    <li><a href="#">功能1</a></li>
                    <li class="divider"></li>
                    <li><a href="#">功能1</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <ul class="nav navbar-nav navbar-right">
      <li>
      	<ul class="nav navbar-nav navbar-left">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    	系统
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="javascript:void(0)" onclick="forwardPage('sys/menuManage')">菜单管理</a></li>
                </ul>
            </li>
            </ul>
       </li>
      <li><a href="#"><span class="glyphicon glyphicon-user"></span> 注册</a></li>
      <li><a href="${pageContext.request.contextPath}/sys/logout.do"><span class="glyphicon glyphicon-log-out"></span> 退出</a></li>
    </ul>
  </div>
</nav>
<div style="height:90%;" id="contentMain">
	
</div>
</body>
</html>
<script>
	function forwardPage(pageName)
	{
		debugger;
		$("#contentMain").load("${pageContext.request.contextPath}/sys/forwardPage.do?pageName="+pageName);
	}
</script>