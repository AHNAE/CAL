<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="model.Store" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品選択</title>
<link rel="stylesheet" href="style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<%@include file = "header-navi.jsp"%>

	<%
		Store store = (Store) session.getAttribute("store");
		Store alreadyUse = (Store) session.getAttribute("already");
		List<Product> listProd;
		List<Product> preListProd;
		int totalCnt = 0;
		if (store == null) {
			listProd = new ArrayList<Product>();
		} else {
			listProd = store.getListProd();
		}
		if(alreadyUse == null){
			preListProd = new ArrayList<Product>();
		}else{
			preListProd = alreadyUse.getListProd();
			for(int i = 0; i < preListProd.size(); i++){
				for(int j = 0; j < listProd.size(); j++){
					if(preListProd.get(i).getId().equals(listProd.get(j).getId()) ){
						listProd.get(j).setCartChk(true);
						totalCnt+=1;
					}
				}
			}
		}
		if (listProd.size() > 0) {
	%>
			<h2>商品選択</h2>
			<form id="form" action="add-prod-servlet" method="POST">
				<input type="hidden"  name="idx" value="">
				<div class="total-data-div">
					カートの内容 : &nbsp;<b><span style="font-size: 20px;" name="totalCnt"><%= totalCnt%>&nbsp;</span></b>件　
					<input id="formBtn" type="submit"  value="選択"/>
				</div>
			</form>
				
				<table class="select-list">
				<tr class="list-text">
					<th style="width: 30px;"><input type="checkbox" id="choice-button" name="chkbox"></th>
					<th style="width: 100px;">イメージ</th>
					<th style="width: 80px;">商品ID</th>
					<th style="width: 400px;">商品名</th>
					<th style="width: 100px;">価格</th>
					<th style="width: 65px;">カート有無</th>
				</tr>
		<%
				for (int idx = 0; idx < listProd.size(); idx++) {
					Product prod = listProd.get(idx);
					if(prod.getcartChk()){
			%>
						<tr style="background-color: #f7ff88;">
							<td><input type="checkbox" id="choice-button"  class="chkbox" value="<%=idx %>" checked></td>
							<td><img src="<%=prod.getImagePath() %>" width="50px" /></td>
							<td><%=prod.getId() %></td>
							<td><%=prod.getName() %></td>
							<td><%=prod.getPriceString() %></td>
							<td><p>O</p></td>
						</tr>			
			<%
					}else{
			%>
						<tr>
							<td><input type="checkbox" id="choice-button" class="chkbox" value="<%=idx %>"></td>
							<td><img src="<%=prod.getImagePath() %>" width="50px" /></td>
							<td><%=prod.getId() %></td>
							<td><%=prod.getName() %></td>
							<td><%=prod.getPriceString() %></td>
							<td><p id="choice-button">-</p></td>
						</tr>			
			<%			
				 } // if end
				%>
			<%			
				} // for end
		%>
				</table>
		

	<%
		}
	%>

</body>
</html>
<script>
	const idx = [];
	const checkedValues = [];
	function initializeCheckedBoxes() {
	    $(".chkbox:checked").each(function() {
	        checkedValues.push($(this).val());  // 체크된 항목의 value를 배열에 추가
	    });
	    return checkedValues;
	}
	
	const checkedValuesInit = initializeCheckedBoxes();
	
	$("input[name=chkbox]").click(function(){
	    const checkboxes = $(".chkbox:checked");
	    const idxArray = [];  // idx 값을 저장할 배열

	    // 체크된 항목의 value를 배열에 추가
	    checkboxes.each(function() {
	        idxArray.push(this.value);
	    });
	    
	    // 전체선택/해제 시 기존 체크된 값만 유지
	    if($(this).is(":checked") === true) {
	        $(".chkbox").prop("checked", true);  // 전체 선택
	    } else {
	        // 전체 해제 시 기존에 체크된 값만 남기고 나머지는 해제
	        $(".chkbox").each(function() {
	            const checkboxValue = $(this).val();
	            // 만약 해당 체크박스의 value가 checkedValuesInit 배열에 있다면 체크를 유지
	            if (checkedValuesInit.includes(checkboxValue)) {
	                $(this).prop("checked", true);  // 기존 체크값 유지
	            } else {
	                $(this).prop("checked", false);  // 나머지는 해제
	            }
	        });
	    }
	});


   $(".chkbox").click(function(){
        const checkboxes = $(".chkbox:checked");
        const idxArray = [];  // idx 값을 저장할 배열

        checkboxes.each(function() {
            idxArray.push(this.value);  // 체크된 항목의 value를 배열에 추가
        });

        // 기존 idx[] input들을 제거
        $("input[name='idx[]']").remove();

        // 새로운 idx[] input을 동적으로 생성하여 폼에 추가
        idxArray.forEach(function(idxValue) {
            $("<input>").attr({
                type: "hidden",
                name: "idx[]",   // 배열 형식으로 설정
                value: idxValue
            }).appendTo("#form");
        });

        console.log("idx array: ", idxArray);
    });
</script>