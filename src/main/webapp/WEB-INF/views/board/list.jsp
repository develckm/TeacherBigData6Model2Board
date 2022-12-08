<%@ page import="java.util.List" %>
<%@ page import="com.bd6.board.dto.BoardDto" %>
<%@ page import="com.bd6.board.dto.PagingDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
<%
    List<BoardDto> boardList=(List<BoardDto>)request.getAttribute("boardList");
    PagingDto paging=(PagingDto)request.getAttribute("paging");
%>
    <main class="container">
        <h1 class="my-5">게시글 리스트</h1>
        <table class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th class="col-1">no</th>
                    <th class="col-4">제목</th>
                    <th class="col-2">게시일</th>
                    <th class="col-2">작성자</th>
                    <th class="col-1">조회</th>
                </tr>
            </thead>
            <tbody>
                <%for(BoardDto board:boardList){%>
                <tr onclick="location.href='detail.do?boardNo='+<%=board.getBoardNo()%>">
                    <td><%=board.getBoardNo()%></td>
                    <td><%=board.getTitle()%></td>
                    <td><%=board.getPostTime()%></td>
                    <td><%=board.getUserId()%></td>
                    <td><%=board.getViews()%></td>
                </tr>
                <%}%>
            </tbody>
        </table>
        <%@include file="pagingNav.jsp"%>

    </main>
</body>
</html>
