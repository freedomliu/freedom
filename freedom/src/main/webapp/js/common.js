var sub = function(data) {
	$("input[name='username']").val("lxt");
	$("input[name='password']").val("lxt");
	$("input[name='imgCode']").val("${code}");
	$("#formUser").submit();
}

function myAjax(url,data,isAsync,sub)
{
	var bean=new Object();
	bean.url=url;
	bean.data=data;
	bean.type="post";
	bean.async=isAsync;
	bean.success= myFunction;
	$.ajax(bean);
}
