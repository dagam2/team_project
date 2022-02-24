<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>여행 플랜 만들기</title>
<style>
div {border: 1px dotted black; display:inline-block;}
#left {
 width: 25%; 
}
#right {
flex-grow: 1;
}
</style>
</head>
<body>
<h3> 여행 플랜 만들기</h3>
<hr>
<div style=" width:100%; display:flex; ">
	<div id="left">
		<jsp:include page="viewMemberList.jsp" />
	</div>
	<div id="right">
		<p id='show'></p>
	</div>
</div>

</body>
</html>