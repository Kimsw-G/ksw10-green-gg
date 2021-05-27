<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/green.css">
    <script defer src="/js/green.js"></script>
</head>
<body>
<script>
    console.log('hi');
</script>
    <table>
        <tr>
        <c:forEach var="date" items="${dates}">
            <th>${date}</th>
        </c:forEach>
        </tr>
    <c:forEach begin="1" end="25" var="i">
        <tr>
        <c:forEach begin="1" end="7" var="j">
            <td class="${list[i*7+j-dataKey].matchCnt}" >
                <p class="arrow_box">
                    ${list[i*7+j-dataKey].year}.${list[i*7+j-dataKey].month}.${list[i*7+j-dataKey].day}<br>
                    ${list[i*7+j-dataKey].matchCnt}
                </p>
            </td>
        </c:forEach>
        </tr>
    </c:forEach>

    </table>
</body>
</html>