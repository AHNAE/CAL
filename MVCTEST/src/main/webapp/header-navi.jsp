<%@ page pageEncoding="UTF-8"%>
<%@ page import="model.Cart" %>
<%@ page import="model.Store" %>

	<%-- 店舗名・ユーザIDの表示 --%>
	<% 
		Store storeHdr = (Store) session.getAttribute("store");
		Cart cartHdr = (Cart) session.getAttribute("cart");
		if ((storeHdr == null) || cartHdr == null) {
			request.setAttribute("errorMsg", "再ログインをお願いします。");	
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
	%>
			<h1><%=storeHdr.getName() %></h1>
			<div id="img-div"><img src="./image/gyaru.jpg" width="180px" /></div>
			<%-- ナビ表示 --%>
			　　　<div id="menu-bar" ><a href="select.jsp" style="color: white;">商品選択</a></div>
			　　　<div id="menu-bar" ><a href="cart.jsp"   style="color: white;">カート内一覧</a></div>
			　　　<div id="menu-bar" ><a href="logout-servlet"   style="color: white;">ログアウト</a></div>
			　　　<b><%=cartHdr.getUserId() %></b>　様
			<hr>
	<%
		}
	%>
