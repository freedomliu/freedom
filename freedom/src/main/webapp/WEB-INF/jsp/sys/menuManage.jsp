<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
<body>
	<br>
	<h1>在这个页面实现功能点</h1>
	<div class="container">
	<div class="row">
			<div class="col-lg-6">
				<div class="input-group">
					<span class="input-group-btn">
						<button class="btn btn-default" onclick="$('input[id=file1]').click();" type="button">
							选择文件
						</button>
					</span>
					<input type="file"  id="file1"  style="display:none;width: 0px">
					<input type="text"  id="file1_text" readonly class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-default" id="upload" type="button">
							上传
						</button>
					</span>
				</div>
			</div>
			
			
			<div class="col-lg-6">
				<div class="input-group">
						<button class="btn btn-default" onclick="download1()" type="button">
							普通下载
						</button>

						<button class="btn btn-default" onclick="download2()" type="button">
							打包下载
						</button>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="input-group">
					<button class="btn btn-default" onclick="download3()" type="button">
						创建excel并下载
					</button>
					<button class="btn btn-default" onclick="activemqTest()" type="button">
						activemqTest—java實現
					</button>
					<button class="btn btn-default" onclick="activemqTest1()" type="button">
						activemqTest-配置文件實現
					</button>
					<button class="btn btn-default" onclick="websocketTest()" type="button">
						[websocket]
					</button>
					<button class="btn btn-default" onclick="redisTest()" type="button">
						[redis]
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
// websocket demo 
var websocket = null;
// 页面第一次记录sessionid
var isFirst=true;
var sessionid=null;
//判断当前浏览器是否支持WebSocket
debugger;
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:80/freedom/WebSocket");
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function () {
    setMessageInnerHTML("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    setMessageInnerHTML("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
	debugger;
	if(isFirst)
	{
		alert("sessionId->"+event.data);
		sessionid=event.data;
	}
	else
	{
		alert(event.data);
	}
	isFirst=false;
}

//连接关闭的回调方法
websocket.onclose = function () {
	debugger;
    setMessageInnerHTML("WebSocket连接关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
	debugger;
    closeWebSocket();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    alert(innerHTML);
}

//关闭WebSocket连接
function closeWebSocket() {
	debugger;
    websocket.close();
}

//发送消息
function send() {
    websocket.send("向服务器发送消息！！！");
}
// websocket demo end
	
	
	$(function() {
		$("#upload").click(function() {
			//$("#imgWait").show();
			var formData = new FormData();
			formData.append("file", document.getElementById("file1").files[0]);
			$.ajax({
				url : "demo/fileuploadExcel",
				type : "POST",
				data : formData,
				contentType : false,
				processData : false,
				success : function(data) {
					//data = eval("(" + data + ")");
						alert(data);

					/* $("#imgWait").hide(); */
				},
				error : function() {
					alert("上传失败！");
					//$("#imgWait").hide();
				}
			});
		});
	});
	
	$('input[id=file1]').change(function() 
		{  
			$('#file1_text').val($(this).val());  
		});  
	
	function download1()
	{
		window.location.href="demo/download1.do";
	}
	
	function download2()
	{
		window.location.href="demo/download2";
	}
	
	function download3()
	{
		window.location.href="demo/download3";
	}
	
	function websocketTest()
	{
		debugger;
		send();
		//$.post("http://localhost/freedom/demo/websocket",{"sessionid":sessionid},null);
		$.ajax({
			data:{"sessionid":sessionid},
			url : "demo/websocket",
			type : "POST",
			success : function(data)
			{
				debugger;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				debugger;
			}
		});
	}
	function redisTest()
	{
		$.ajax({
			url : "demo/redisTest",
			type : "POST",
			success : function(data)
			{
				debugger;
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				debugger;
			}
		});
	}
	
	function activemqTest()
	{
		$.ajax({
			url : "demo/activemqTest",
			type : "POST"
		});
	}
	function activemqTest1()
	{
		$.ajax({
			url : "demo/activemqTest1",
			type : "POST"
		});
	}
</script>

</html>