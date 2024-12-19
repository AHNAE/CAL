<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div id="login-body-div">
		<div id="login-form">
			<h1>キャルのガールのギャル向けの</h1>
			<h2>キャルサービス ログイン画面</h2>
			</br>
			<form action="login-servlet" method="post" >
				<div id="first-form-div">
					<div id="id-div">
						<p id="form-box">ユーザ　ID　： </p><p id="form-contents"><input type="text" id="id" name="userId" required></p>
					</div>	
					<div id="pass-div">
						<p id="form-box">パスワード　 ： </p><p id="form-contents"><input type="password" id="password" name="password" required></p><br><br>
					</div>
				</div>
				</br>
				</br>
				<input type="submit" value="ログイン">
			</form>
		</div>
	</div>
	<% 
		String errorMsg = (String)request.getAttribute("errorMsg");
		if (errorMsg != null) {
	%>
			<p class="error-msg"><%= errorMsg %></p>
	<%
		} 
	%>

</body>
</html>
