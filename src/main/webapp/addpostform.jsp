<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>회원추가 - 실전프로젝트 회원관리 V1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
</head>
<body>

<h1>신규 회원 추가</h1>
<form action="addpost.jsp" method="post" enctype="multipart/form-data">
<table>
<tr><td>User ID:</td><td><input type="text" name="userid"/></td></tr>
<tr><td>Password:</td><td><input type="password" name="password"/></td></tr>
<tr><td>Username:</td><td><input type="text" name="username"/></td></tr>
<tr><td>Email:</td><td><input type="email" name="email"/></td></tr>
<tr><td>Blog URL:</td><td><input type="text" name="blogurl"/></td></tr>
<tr><td>Photo:</td><td><input type="file" name="photo"/></td></tr>
<tr><td>Detail:</td><td><textarea cols="50" rows="5" name="detail"></textarea></td></tr>
<tr><td><a href="posts.jsp">View All Records</a></td><td align="right"><input type="submit" value="Add Post"/></td></tr>
</table>
</form>

</body>
</html>