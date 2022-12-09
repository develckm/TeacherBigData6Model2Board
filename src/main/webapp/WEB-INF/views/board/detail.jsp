<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">게시글 상세 페이지</h1>
        <h2>1:N 조인 관계에서 N(reply,board_img,board_prefer)을 불러오는 방법</h2>
        <ol>
            <li>조인 sql 을 작성해서 BoardDao 에 서비스 모델을 추가 (board_img 권장)</li>
            <li>서브쿼리로 집계함수를 작성하고 BoardDto 에 필드를 추가(board_prefer 권장)</li>
            <li>별도의 dao 를 작성하고 Service 에서 호출 (Reply 권장)</li>
        </ol>
        <p><%=request.getAttribute("board")%></p>
    </main>
</body>
</html>
