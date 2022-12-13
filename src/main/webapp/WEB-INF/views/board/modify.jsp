<%@ page import="com.bd6.board.dto.BoardDto" %>
<%@ page import="com.bd6.board.dto.BoardImgDto" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 등록</title>
<style>
    .imgContainer input[type=checkbox]:checked+img{
        background-color: var(--bs-danger);
    }

</style>

</head>
<body>
    <% BoardDto board= (BoardDto) request.getAttribute("board");%>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">게시글 등록 폼</h1>
        <form action="modify.do" method="post" enctype="multipart/form-data">
            <p class="form-floating">
                <input value="<%=board.getBoardNo()%>" readonly
                       type="text" name="boardNo" class="form-control" placeholder="boardNo">
                <label>글번호</label>
            </p>

            <p class="form-floating">
                <input value="<%=board.getUserId()%>" readonly
                        type="text" name="userId" class="form-control" placeholder="userId">
                <label>글쓴이</label>
            </p>
            <p class="form-floating">
                <input value="<%=board.getTitle()%>"
                        type="text" name="title" class="form-control" placeholder="title">
                <label>타이틀</label>
            </p>
            <div class="row imgContainer mb-3">
                <h4>이미지 삭제</h4>
                <%if(board.getBoardImgList()!=null){
                    for (BoardImgDto boardImg : board.getBoardImgList()){%>
                    <label class="col-12 col-md-6 col-lg-3">
                        <input type="checkbox" name="delBoardImgNo" value="<%=boardImg.getBoardImgNo()%>">
                        <img src="<%=request.getContextPath()%>/public/img/<%=boardImg.getImgPath()%>" alt="게시글 이미지" class="img-thumbnail">
                    </label>
                <%}
                }%>
            </div>

            <p class="input-group">
                <label class="input-group-text">이미지1</label>
                <input type="file" name="imgFile1" class="form-control">
            </p>
            <p class="input-group">
                <label class="input-group-text">이미지2</label>
                <input type="file" name="imgFile2" class="form-control">
            </p>
            <p class="input-group">
                <label class="input-group-text">이미지3</label>
                <input type="file" name="imgFile3" class="form-control">
            </p>
            <p class="input-group">
                <label class="input-group-text">이미지4</label>
                <input type="file" name="imgFile4" class="form-control">
            </p>

            <div class="form-floating mb-3">
                <textarea name="contents" style="height: 200px;resize: none;" class="form-control" cols="30" rows="10"><%=board.getContents()%></textarea>
                <label>내용</label>
            </div>
            <div class="mb-5 d-flex justify-content-end">
                <button class="btn btn-outline-warning me-3" type="reset">초기화</button>
                <button class="btn btn-outline-primary" type="submit">등록</button>
            </div>
        </form>
    </main>
</body>
</html>
