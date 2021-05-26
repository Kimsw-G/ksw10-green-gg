<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/green.css">
    <script defer src="js/green.js"></script>
</head>
<body>
    <table>
        <tr>
        <c:forEach var="date" items="${dates}">
            <th>${date}</th>
        </c:forEach>
        </tr>
    <c:forEach begin="0" end="24" var="i">
        <tr>
        <c:forEach begin="1" end="7" var="j">
            <td class="${list[i*7+j-dataKey].totalGames}" >
        </c:forEach>
        </tr>
    </c:forEach>

    </table>
</body>
</html>