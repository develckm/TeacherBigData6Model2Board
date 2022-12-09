<%@ page import="com.bd6.board.dto.BoardDto" %>
<%@ page import="com.bd6.board.dto.ReplyDto" %>
<%@ page import="com.bd6.board.dto.PagingDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 상세</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <%
    BoardDto board=(BoardDto) request.getAttribute("board");
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
            <h2>댓글 리스트</h2>
            <%if(board.getReplyList()!=null){%>
                <div class="list-group">
                    <%for (ReplyDto reply : board.getReplyList()){%>
                    <a href="#" class="list-group-item py-4">
                        <div class="d-flex w-100 justify-content-between">
                            <h5><%=reply.getTitle()%></h5>
                            <small><%=reply.getPostTime()%></small>
                        </div>
                        <p class="d-flex justify-content-end">
                            <small class="pe-3">
                                글번호 : <%=reply.getReplyNo()%>
                            </small>
                            <small class="pe-3">
                                좋아요 : 5
                            </small>
                            <small class="pe-3">
                                싫어요 : 3
                            </small>
                            <small>
                                작성자 : <%=reply.getUserId()%>
                            </small>

                        </p>
                        <div><%=reply.getContents()%></div>
                    </a>
                    <%}%>
                </div>
                <%PagingDto paging=(PagingDto) request.getAttribute("paging");%>
                <%@include file="pagingNav.jsp"%>
            <%}%>
        </section>

    </main>
</body>
</html>
