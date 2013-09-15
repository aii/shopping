<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
		function check(f) {
			//user = document.myForm.userid.value;
			customerId = f.customerId.value;
			customerPwd = f.customerPwd.value;
			if (customerId == null || customerId.length == 0 || customerPwd == null
					|| customerPwd.length == 0) {
				alert("用户名或密码为空。");
				return false;
			} else {
				return true;
			}
		}
	</script>
<body>
	<center>
		<h2>用户登陆</h2>
		<form action="/shopping/LoginServlet" method="post" onSubmit="return check(this);">
			<table border="1">
				<tr>
					<td>用户名</td>
					<td><input type="text" name="customerId" value="${customer.customerId }"><br /></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="customerPwd" value="${customer.customerPwd }"><br /></td>
				</tr>
				<tr>
				<td>Cookie选项：</td>
				<td><input type="radio" name="cookie" value="nosave" checked>不保存
					<input type="radio" name="cookie" value="save">保存60秒</td>
				</tr>
				<tr>
					<td><input type="submit" value="登陆"></td>
					<td>
						<input type="reset" value="重置">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>