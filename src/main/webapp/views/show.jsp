
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h>Your choice</h>
<br/>

id:    <%=request.getSession().getAttribute("idObj")%><br/>
UserId:<%=request.getSession().getAttribute("userId")%><br/>
Text:  <%=request.getSession().getAttribute("text")%>

</body>
</html>
