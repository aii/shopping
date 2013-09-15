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
	function back() {
		window.location.href = "/shopping/EnquireGoodsServlet";
	}
	function add(pid){
		window.location.href = "/shopping/UpdateGoodsServlet?pid="+pid+"&operate=add";
	}
	function sub(pid){
		window.location.href = "/shopping/UpdateGoodsServlet?pid="+pid+"&operate=sub";
	}
	function del(pid){
		var result = confirm("确认删除？");
		if(result == true){
			window.location.href = "/shopping/DeleteGoodsServlet?pid="+pid;
		}else{
			
		}
	}
	function emptyCart(){
		var result = confirm("确认清空购物车？");
		if(result == true){
			window.location.href = "/shopping/EmptyCartServlet";
		}
	}
</script>
<body>
	<c:choose>
		<c:when test="${sessionScope.cart == null || sessionScope.cart.items.size() == 0 }">
			<h2>
				购物车没有物品，点击<a href="/shopping/EnquireGoodsServlet">这里</a>返回商品列表。
			</h2>
		</c:when>
		<c:otherwise>
			<center>
				<h2>购物车清单</h2>
				<table border="1">
					<tr>
						<td>宝贝</td>
						<td>单价</td>
						<td>数量</td>
						<td>小计</td>
						<td>增加</td>
						<td>减少</td>
						<td>删除</td>
					</tr>
					<c:forEach items="${sessionScope.cart.items }" var="item">
						<tr>
							<td>${item.value.good.name }</td>
							<td>${item.value.good.price }</td>
							<td>${item.value.quantity }</td>
							<td>${item.value.price }</td>
							<td><input type="button" value="增加"
								onclick="add(${item.value.good.pid });"></td>
							<c:choose>
								<c:when test="${item.value.quantity == 1 }">
									<td><input type="button" value="减少"
										onclick="sub(${item.value.good.pid });" disabled="disabled"></td>
								</c:when>
								<c:otherwise>
									<td><input type="button" value="减少"
										onclick="sub(${item.value.good.pid });"></td>
								</c:otherwise>
							</c:choose>
							<td><input type="button" value="删除"
								onclick="del(${item.value.good.pid });"></td>
						</tr>
					</c:forEach>
				</table>
				总计：${sessionScope.cart.price }<br /> 
				<input type="button" value="返回" onclick="back();"> 
				<input type="button" value="清空购物车" onclick="emptyCart();">
			</center>
		</c:otherwise>
	</c:choose>
</body>
</html>