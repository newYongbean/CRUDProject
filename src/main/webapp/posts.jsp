<%--
  Created by IntelliJ IDEA.
  User: yongbeanchung
  Date: 2022/11/18
  Time: 5:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="com.example.dao.MemberDAO, com.example.bean.MemberVO, java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <title>실전프로젝트 회원관리 V1</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member.css">
  <script>
    function delete_ok(id){
      var a = confirm("정말로 삭제하겠습니까?");
      if(a) location.href='deletepost.jsp?id=' + id;
    }
  </script>
</head>
<body>
<h1>회원관리 페이지</h1>
<%
  MemberDAO boardDAO = new MemberDAO();
  ArrayList<MemberVO> list = boardDAO.getList();
  request.setAttribute("list",list);
%>
<table id="list" width="90%">
  <tr>
    <th>No</th>
    <th>User ID</th>
    <th>User Name</th>
    <th>Email</th>
    <th>blog URL</th>
    <th>Photo</th>
    <th>Detail</th>
    <th>Registered Date</th>
    <th>Edit</th>
    <th>Delete</th>
  </tr>
  <c:forEach items="${list}" var="u">
    <tr>
      <td>${u.getSid()}</td>
      <td>${u.getUserid()}</td>
      <td>${u.getUsername()}</td>
      <td>${u.getEmail()}</td>
      <td>${u.getBlogurl()}</td>
      <td><c:if test = "${u.getPhoto() ne ''}"><br />
        <img src="${pageContext.request.contextPath }/upload/${u.getPhoto()}" class="photo" width="250"></c:if></td>
      <td>${u.getDetail()}</td>
      <td>${u.getRegdate()}</td>
      <td><a href="editform.jsp?id=${u.getSid()}">Edit</a></td>
      <td><a href="javascript:delete_ok('${u.getSid()}')">Delete</a></td>
    </tr>
  </c:forEach>
</table>
<br/><a href="addpostform.jsp">Add New Post</a>
</body>
</html>