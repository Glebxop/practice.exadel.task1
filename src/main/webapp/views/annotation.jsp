<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workwithannotation</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-dark-gray"><div>
    <h1>Add annotation</h1>
    <form action="${pageContext.request.contextPath}/anotation" method="post">
        <label>Text:
            <input type="text" name="text"><br/>
        </label>
        <label>id:
            <input type="number" name="id"><br/>
        </label>
        <label>title:
            <input type="text" name="title"><br/>
        </label>
        <label>User name:
            <input type="text" name="name"><br/>
        </label>
        <label>Email:
            <input type="text" name="email"><br/>
        </label>

        <input type="hidden" name="command" value="addComment"/>

        <button  type="submit">Add anotation</button>
    </form>
    <h1>Get annotation</h1>
    <form action="${pageContext.request.contextPath}/anotation" method="post"><label>id:
        <input type="number" name="id"><br/>
    </label>
        <input type="hidden" name="command" value="getComment"/>

        <button type="submit">Get anotation</button>
    </form>

    <h1>Dell anotation</h1>
    <form action="${pageContext.request.contextPath}/anotation" method="post">
        <label>id:
            <input type="number" name="id"><br/>
        </label>
        <input type="hidden" name="command" value="dellComment"/>

        <button type="submit">Dell anotation</button>
    </form>
    <h1>Update anotation</h1>
    <form action="${pageContext.request.contextPath}/anotation" method="post">
        <label>Text:
            <input type="text" name="text"><br/>
        </label>
        <label>id:
            <input type="number" name="id"><br/>
        </label>
        <label>title:
            <input type="text" name="title"><br/>
        </label>
        <label>User name:
            <input type="text" name="name"><br/>
        </label>
        <label>Email:
            <input type="text" name="email"><br/>
        </label>

        <input type="hidden" name="command" value="upComment"/>

        <button type="submit">Update anotation</button>
    </form>

</div>
</body>
</html>