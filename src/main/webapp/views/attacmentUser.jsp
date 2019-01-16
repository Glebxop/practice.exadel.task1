<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workwithattacment</title><link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray"><div>

    <h1>Get attacment</h1>
    <form action="${pageContext.request.contextPath}/attacment" method="post"><label>id:
        <input type="number" name="id"><br/>
    </label>
        <input type="hidden" name="command" value="getComment"/>

        <button type="submit">Get attacment</button>
    </form>



</div>
</body>
</html>