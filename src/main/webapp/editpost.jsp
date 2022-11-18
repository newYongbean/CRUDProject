<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.example.dao.MemberDAO" %>
<%@ page import="com.example.crudproject.FileUpload" %>
<%@ page import="com.example.bean.MemberVO" %>

<%
	MemberDAO MemberDAO = new MemberDAO();
	FileUpload upload = new FileUpload();
	MemberVO u = upload.uploadPhoto(request);
	int i = MemberDAO.updateMember(u);
	response.sendRedirect("posts.jsp");
%>