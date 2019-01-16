
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray">
<h>Your choice</h>
<br/>

id:    <%=request.getSession().getAttribute("idObj")%><br/>
UserId:<%=request.getSession().getAttribute("userId")%><br/>
Text:  <%=request.getSession().getAttribute("text")%>

</body>
</html>
