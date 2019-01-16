<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workwithannotation</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray"><div>

    <h1>Get annotation</h1>
    <form action="${pageContext.request.contextPath}/anotation" method="post"><label>id:
        <input type="number" name="id"><br/>
    </label>
        <input type="hidden" name="command" value="getComment"/>

        <button type="submit">Get anotation</button>
    </form>


</div>
</body>
</html>