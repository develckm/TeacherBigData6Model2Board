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
            <li>
                조인 sql 을 작성해서 BoardDao 에 서비스 모델을 추가 (board_img 권장)
                <ul>
                    <li>조인되는 테이블의 서비스가 종속적이어야 한다.</li>
                    <li>서비스가 간단해야한다.</li>
                </ul>
            </li>
            <li>서브쿼리로 집계함수를 작성하고 BoardDto 에 필드를 추가(board_prefer 권장)</li>
            <li>
                별도의 dao 를 작성하고 Service 에서 호출 (Reply 권장)
                <ul>
                    <li>복잡한 서비스를 구현하고 재사용 가능(paging,self join,ajax crud)</li>
                    <li>fetch lazy 를 지원하는 lib 를 사용가능</li>
                </ul>
            </li>
        </ol>
        <p><%=request.getAttribute("board")%></p>
    </main>
</body>
</html>
