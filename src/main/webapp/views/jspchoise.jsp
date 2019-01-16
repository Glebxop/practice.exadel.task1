<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>choise</title><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray">
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
