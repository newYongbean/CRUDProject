<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.dao.MemberDAO" %>
<%@ page import="com.example.bean.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>정보수정 - 실전프로젝트 회원관리 V1</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
</head>
<body>

<%
	MemberDAO MemberDAO = new MemberDAO();
	String id=request.getParameter("id");	
	MemberVO u =MemberDAO.getOne(Integer.parseInt(id));
	request.setAttribute("vo", u);
%>

<h1>회원 정보 수정</h1>
<form action="editpost.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="sid" value="<%=u.getSid() %>"/>
<table>
<tr><td>User ID:</td><td><input type="text" name="userid"/></td></tr>
<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
<tr><td>Username:</td><td><input type="text" name="username"/></td></tr>
<tr><td>Email:</td><td><input type="email" name="email"/></td></tr>
<tr><td>Blog URL:</td><td><input type="text" name="blogurl"/></td></tr>
<tr><td>Photo:</td><td><input type="file" name="photo"/>
	<c:if test = "${u.getPhoto() ne ''}"><br />
		<img src="${pageContext.request.contextPath }/upload/${u.getPhoto()}" class="photo" width="250"></c:if></td></tr>
<tr><td>Detail:</td><td><textarea cols="50" rows="5" name="detail"></textarea></td></tr>
<tr><td colspan="2"><input type="submit" value="Edit Post"/>
<input type="button" value="Cancel" onclick="history.back()"/></td></tr>
</table>

</form>

</body>
</html>