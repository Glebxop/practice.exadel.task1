<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray">

<h1>Log in</h1>

<form action="${pageContext.request.contextPath}/log"   method="post">
    <label>Name:
        <input type="text" name="name"><br/>
    </label>
    <label>Email:
        <input type="email" name="email"><br/>
    </label>
    <input type="hidden" name="command" value="Registration"/>

    <button  type="submit">Registration</button>
</form>




</body>
</html>
