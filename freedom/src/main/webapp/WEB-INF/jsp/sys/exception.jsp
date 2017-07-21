<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common.jsp"%>
<html>
  <body style="background-color:#87CEEB;">
  	<div class="Absolute-Center" style="height: 500px;width: 700px;text-align: center;">
    <h1><font face="verdana">系统异常</font></h1><br>
    <a href="${pageContext.request.contextPath}/main.do">返回主页</a><br>
    <label ><c:out value="${exception}" /></label> 
    </div>
  </body>
</html>
