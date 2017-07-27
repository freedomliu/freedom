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
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
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
					debugger;
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