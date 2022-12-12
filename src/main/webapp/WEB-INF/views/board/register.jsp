<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시글 등록</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">게시글 등록 폼</h1>
        <h2>BLOB 과 multipart/form-data </h2>
        <ul>
            <li>BLOB: 바이너리 데이터를 text 인코딩으로 분석하지 않겠다. ->
                텍스트가 아닌 다른 파일 형식으로 데이터를 분석하겠다.
            </li>
            <li>multipart/form-data : form 에서 파라미터를 보낼때 파일(BLOB)도 같이 보내겠다. </li>
            <li>application/x-www-form-urlencoded :
                form 에서 파라미터를 보내는 default 값으로
                queryString 형식으로 파라미터를 요청 본문 해더에 포함해서 보낸다.
            </li>
            <li>COS : 자바 서블릿에서 BLOB 으로 넘어온 파라미터를 처리해주는 라이브러리 </li>
        </ul>
        <form action="register.do" method="post" enctype="multipart/form-data">

            <p class="form-floating">
                <input value="develckm"
                        type="text" name="userId" class="form-control" placeholder="title">
                <label>글쓴이</label>
            </p>
            <p class="form-floating">
                <input value="타이틀 입니다~ 안녕하세요!!"
                        type="text" name="title" class="form-control" placeholder="title">
                <label>타이틀</label>
            </p>
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
                <textarea name="contents" style="height: 200px;resize: none;" class="form-control" cols="30" rows="10">내용입니다.</textarea>
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
