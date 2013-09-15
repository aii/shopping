<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	function checkStatus(element,pid){
		var r = confirm("确认添加？");
		if(r == true){
			document.getElementById("goodInfoId").value=pid;
			document.getElementById("f1").submit();
		}else{
						
		}
	}
	function showCart(){
		window.location.href="/shopping/goods/cart.jsp";
	}
</script>
<body>
	<c:if test="${requestScope.goods != null }">
		<center>
			<h2>商品清单</h2>
			<table border="1">
				<tr>
					<td>pid</td>
					<td>名称</td>
					<td>备注</td>
					<td>单价</td>
					<td>库存</td>
					<td>操作</td>
				</tr>
				<c:forEach items="${requestScope.goods }" var="good">
					<tr>
						<td>${good.pid }</td>
						<td>${good.name}</td>
						<td>${good.note }</td>
						<td>${good.price }</td>
						<td>${good.amount }</td>
						<td><input type="button" id="${good.pid }" value="添加" onclick="checkStatus(this,${good.pid });"></td>
					</tr>
				</c:forEach>
			</table>
		</center>
	</c:if>
	<form id="f1" action="/shopping/AddGoodsServlet" method="post">
		<input type="hidden" name="goodPid" id="goodInfoId" value="">
	</form>
	<center>
		<input type="button" value="查看购物车" onclick="showCart();">
	</center>
</body>
</html>