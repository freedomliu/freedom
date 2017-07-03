function myAjax(url,data,myFunction)
{
	$.ajax({
		url : url,
		data:data,
		type:"post",
		success : myFunction
	});
}