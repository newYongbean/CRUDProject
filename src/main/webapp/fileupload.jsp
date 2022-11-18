<%--
  Created by IntelliJ IDEA.
  User: yongbeanchung
  Date: 2022/11/18
  Time: 3:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.File" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>파일 업로드 결과 </title>
</head>
<body>
<%
    String filename = "";
    int sizeLimit = 15 * 1024 * 1024;

    String realPath = request.getServletContext().getRealPath("upload");
    File dir = new File(realPath);
    if(!dir.exists()) dir.mkdirs();

    MultipartRequest multpartRequest = null;
    multpartRequest = new MultipartRequest(request, realPath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());

    filename = multpartRequest.getFilesystemName("photo");
%>

파일명 : <%=filename%><br/>
<img src="${pageContext.request.contextPath }/upload/<%=filename%>">
</body>
</html>
