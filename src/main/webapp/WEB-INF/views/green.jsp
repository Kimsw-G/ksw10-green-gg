<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="/css/green.css">
    <script defer src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script defer src="/js/green.js"></script>
</head>
<body>
    <div>

        <img src="http://ddragon.leagueoflegends.com/cdn/11.12.1/img/profileicon/${info.profileIconId}.png">
        ${info.name}
        <a id="kakao-link-btn" href="javascript:sendLink()">
            <img
                    src="https://developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png"
            />
        </a>
    </div>
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

    <footer>
        <a href="https://github.com/Kimsw-G/ksw10-green-gg">https://github.com/Kimsw-G/ksw10-green-gg</a>
    </footer>
</body>
</html>