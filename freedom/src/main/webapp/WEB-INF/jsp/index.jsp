<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<html>
<body>
<div id="loginBox" style="height: 150;width:200;">
	<div class="input-group">
	  <span class="input-group-addon ">
	  	<span class="glyphicon glyphicon-user"></span>
	  </span> 
	  <input type="text" class="form-control" placeholder="用户名">
	</div>
	
	<div class="input-group">
	  <span class="input-group-addon ">
	  	<span class="glyphicon glyphicon-lock"></span>
	  </span>
	  <input type="password" class="form-control" placeholder="密码">
	</div>
	<div>
		<img alt="" src="${pageContext.request.contextPath}/login/getImageCode.do">
	</div>
</div>
</body>
<script>
 $(window).resize(function(){ 
	     $("#loginBox").css({ 
	         position: "absolute", 
	         left: ($(window).width() - $("#loginBox").outerWidth())/2, 
	         top: ($(window).height() - $("#loginBox").outerHeight())/2 
	     });        
	 }); 
 
  $(function()
  { 
	      $(window).resize(); 
  }); 
</script>
</html>
