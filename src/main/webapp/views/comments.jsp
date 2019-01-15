<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>workwithcomment</title>
    <link rel="stylesheet" href=https://www.w3schools.com/w3css/4/w3.css>
</head>
<body><div>
<h1>Add comment</h1>
<form action="${pageContext.request.contextPath}/comments" method="post">
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

    <button  type="submit">Add comment</button>
</form>
<h1>Get comment</h1>
<form action="${pageContext.request.contextPath}/comments" method="post"><label>id:
    <input type="number" name="id"><br/>
</label>
    <input type="hidden" name="command" value="getComment"/>

    <button type="submit">Get comment</button>
</form>

<h1>Dell comment</h1>
<form action="${pageContext.request.contextPath}/comments" method="post">
    <label>id:
        <input type="number" name="id"><br/>
    </label>
    <input type="hidden" name="command" value="dellComment"/>

    <button type="submit">Dell comment</button>
</form>
<h1>Update comment</h1>
<form action="${pageContext.request.contextPath}/comments" method="post">
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

    <button type="submit">Update comment</button>
</form>
<h1>Download CSV file of comments</h1>
<form action="${pageContext.request.contextPath}/comments"  method="post" enctype="multipart/form-data">

    <input type="file" name="file" />
    <input type="submit" />
    <input type="hidden" name="command" value="file"/>
</form>
</div>
</body>
</html>
