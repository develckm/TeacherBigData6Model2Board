<%@ page import="com.bd6.board.dto.BoardDto" %>
<%@ page import="com.bd6.board.dto.ReplyDto" %>
<%@ page import="com.bd6.board.dto.PagingDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <%
    BoardDto board=(BoardDto) request.getAttribute("board");
    PagingDto paging=(PagingDto) request.getAttribute("paging");
    %>

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
        <p>게시글 번호 : <%=board.getBoardNo()%></p>
        <p>제목 : <%=board.getTitle()%></p>
        <p>작성자 : <%=board.getUserId()%></p>
        <p>조회수 : <%=board.getViews()%></p>
        <p>게시 날짜 : <%=board.getPostTime()%></p>
        <div class="border p-3 rounded-3"><%=board.getContents()%></div>
        <section>
            <h2 class="my-4">
                <a href="#replyList" data-bs-toggle="collapse">
                    <strong>댓글 리스트</strong>
                    <small>
                        <%=paging.getTotalRows()%>
                    </small>
                </a>
            </h2>
            <div id="replyList" class="collapse show">
                <% List<ReplyDto> replyList=board.getReplyList();%>
                <%@include file="/WEB-INF/views/reply/list.jsp"%>
                <%@include file="pagingNav.jsp"%>
            </div>
        </section>

    </main>
</body>
</html>
