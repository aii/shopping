<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<input type="hidden" id="idAdmin" value="${user.isAdmin }">
<script type="text/javascript">
	function go(operateType) {
		var idAdmin = document.getElementById("idAdmin").value;
		if ("0" == idAdmin) {
			if ("E" != operateType) {
				alert("该用户不能执行该操作");
			}else{
				document.ff.action = "./enquire.jsp"; 
			}
		} else {
			if ("C" == operateType) {
				document.ff.action = "../create/create.jsp";
			} else {
				document.ff.action = "./enquire.jsp";
			}
		}
			document.ff.operateType.value = operateType;
			document.ff.submit();
	}
</script>
<body>
	<form name="ff" method="post">
		<a href="javascript:go('C');"><input type="button" value="增加"></a>
		<a href="javascript:go('E');"><input type="button" value="查询"></a>
		<a href="javascript:go('U');"><input type="button" value="更新"></a>
		<a href="javascript:go('D');"><input type="button" value="删除"></a>
		<input type="hidden" name="operateType">
	</form>
	<form action="../LogoutServlet">
		<input type="submit" value="注销">
	</form>
	<form action="../EnquireGoodsServlet">
		<input type="submit" value="购物">
	</form>
</body>
</html>