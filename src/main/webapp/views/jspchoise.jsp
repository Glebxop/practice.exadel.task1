<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choise</title>
</head>
<body>
<div>
    <h1>Make your choice</h1>
</div>


    <div>
        <button onclick="location.href='${pageContext.request.contextPath}/comments.jsp'">Work with Comments</button>
    </div><br/>
    <div>
        <button onclick="location.href='${pageContext.request.contextPath}/attacment.jsp'">Work with Attacments</button>
    </div><br/>
    <div>
        <button onclick="location.href='${pageContext.request.contextPath}/annotation.jsp'">Work with Anotattions</button>
    </div>

</body>
</html>
