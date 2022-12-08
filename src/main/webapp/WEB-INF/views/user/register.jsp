<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/public/css/userRegister.css">
    <script src="<%=request.getContextPath()%>/public/js/userRegister.js" defer></script>
</head>
<style>
</style>
<body>
    <%@include file="/headerNav.jsp"%>
    <main class="container">
        <h1 class="my-5">유저 등록 폼</h1>
        <form action="register.do" method="post" name="registerForm">
            <p class="form-floating">
                <input name="userId" value="" type="text" class="form-control is-valid" placeholder="id">
                <label>아이디 (아이디 4자리 이상 영어입니다.)</label>
                <label class="valid-feedback"> 사용 가능한 아이디 </label>
                <label id="idInvalidMsg" class="invalid-feedback">이미 사용 중인 아이디 입니다.</label>

            </p>
            <p class="form-floating">
                <input name="pw" value="" type="text" class="form-control" placeholder="pw">
                <label class="">비밀번호</label>
                <label class="valid-feedback"> 사용가능한 비밀번호 </label>
                <label class="invalid-feedback">비밀번호 (영어와 숫자를 조합한 8자리 이상 입력하세요!)</label>
            </p>
            <p class="form-floating">
                <input name="name" value="" type="text" class="form-control" placeholder="name">
                <label class="">이름</label>
            </p>
            <p class="form-floating">
                <input name="phone" value="" type="text" class="form-control" placeholder="phone">
                <label class="">폰 번호</label>
            </p>
            <p class="form-floating">
                <input name="email" value="" type="text" class="form-control" placeholder="email">
                <label class="">이메일</label>
            </p>

            <p class="form-floating">
                <input name="birth" value="" type="date" class="form-control" placeholder="birth">
                <label class="">생일</label>
            </p>
            <p>
                <button type="reset" class="btn btn-outline-warning">초기화</button>
                <button type="submit" class="btn btn-outline-primary">등록</button>
            </p>
        </form>
    </main>
</body>
</html>
