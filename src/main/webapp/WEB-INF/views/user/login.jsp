<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 폼</title>
</head>
<body>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">로그인 폼</h1>
        <form action="./login.do" method="post">
            <p class="form-floating">
                <input type="text" name="userId" class="form-control" placeholder="id">
                <label>아이디</label>
            </p>
            <p class="form-floating">
                <input type="text" name="pw" class="form-control" placeholder="pw">
                <label>패스워드</label>
            </p>
            <p class="d-flex justify-content-end">
                <a href="./register.do" class="btn btn-outline-info me-2">회원가입</a>
                <button class="btn btn-outline-primary">로그인</button>
            </p>
        </form>
    </main>
</body>
</html>
