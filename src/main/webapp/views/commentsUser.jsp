<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workwithcomment</title>
    <link rel="stylesheet" href=https://www.w3schools.com/w3css/4/w3.css>
</head>
<body><div>
    </form>
    <h1>Get comment</h1>
    <form action="${pageContext.request.contextPath}/comments" method="post"><label>id:
        <input type="number" name="id"><br/>
    </label>
        <input type="hidden" name="command" value="getComment"/>

        <button type="submit">Get comment</button>
    </form></div>
</body>
</html>
