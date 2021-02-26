<%--
  Created by IntelliJ IDEA.
  User: yhq_e
  Date: 2021/2/18
  Time: 17:18
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    偷懒贴模板代码用
</head>
<body>

$.ajax({

url : "",
data : {

},
type : "",
dataType : "json",
success : function (data) {

}

})


//创建时间为当前系统时间
String createTime = DateTimeUtil.getSysTime();
//创建人为当前用户
String createBy = ((User) request.getSession().getAttribute("user")).getName();





</body>
</html>
