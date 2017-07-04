<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="common.jsp"%>
<style type="text/css">
#top-image {
  position:fixed ;
  top:0;
  width:100%;
  z-index:0;
  height:100%;
}
</style>
<html>
<body>
<div id="top-image">
<img alt="" src="image/bg.jpg" width="100%">
<div class="panel panel-primary" id="loginPanel" style="height: 250px;">
	<div class="panel-heading">
		登陆
	</div>
	<div class="panel-body">
<form id="formUser" method ="Post" action="${pageContext.request.contextPath}/main.do">
<div id="loginBox" style="height: 150;width:250;">
	<div class="input-group">
	  <span class="input-group-addon ">
	  	<span class="glyphicon glyphicon-user"></span>
	  </span> 
	  <input type="text" class="form-control" name="username" placeholder="用户名">
	</div>
	
	<div class="input-group">
	  <span class="input-group-addon ">
	  	<span class="glyphicon glyphicon-lock"></span>
	  </span>
	  <input type="password" name="password" class="form-control" placeholder="密码">
	</div>
	<div style="width: 250px;height: 40;margin-top: 10px" >
	<img style="float:left;" alt="" id="codeImg" onclick="reImg()" src="${pageContext.request.contextPath}/getImageCode.do">
		<input style="float:left;width: 150px" type="text" class="form-control" name="imgCode" placeholder="验证码">
	</div>
	<button type="button" onclick="formSubmit()" style="width: 250;margin-top: 5px" class="btn btn-info" data-loading-text="Loading...">登陆</button>
	<label id="msg" style="color: red;float: right;">${msg}</label>
</div>
</form>
</div>
</div>
</div>
</body>
<script>
 $(window).resize(function(){ 
	     $("#loginPanel").css({ 
	         position: "absolute", 
	         left: ($(window).width() - $("#loginBox").outerWidth())/2, 
	         top: ($(window).height() - $("#loginBox").outerHeight())/2 
	     });        
	 }); 
 
  $(function()
  { 
	  $(window).resize(); 
  });
  
  function formSubmit()
  {
	  debugger;
	  if($("input[name='username']").val()=="")
	  {
		  $("#msg").html("请输入账号");
		  return false;
	  }
	  if($("input[name='imgCode']").val()=="")
	  {
		  $("#msg").html("请输入验证码");
		  return false;
	  }
	  $("#formUser").submit();
  }
  
  function reImg()
  {
	  $("#codeImg").attr('src',"${pageContext.request.contextPath}/getImageCode.do?a="+new Date()); 
  }
</script>
</html>