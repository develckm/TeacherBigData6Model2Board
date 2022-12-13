<%@ page import="com.bd6.board.dto.UserDto" %>
<%@ page pageEncoding="UTF-8" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/public/bootstrap/dist/css/bootstrap.css">
<script src="<%=request.getContextPath()%>/public/bootstrap/dist/js/bootstrap.bundle.js"></script>
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<%=request.getContextPath()%>/">TEACHER_HOME</a>

        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#offcanvasDarkNavbar" aria-controls="offcanvasDarkNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="offcanvas offcanvas-end text-bg-dark" tabindex="-1" id="offcanvasDarkNavbar" aria-labelledby="offcanvasDarkNavbarLabel">
            <div class="offcanvas-header">
                <h5 class="offcanvas-title" id="offcanvasDarkNavbarLabel">관리자 페이지 네비게이션</h5>
                <button type="button" class="btn-close btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
            </div>
            <div class="offcanvas-body">
                <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/bd6Board/">STUDENT_HOME</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            유저 관리
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/user/list.do">리스트</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/user/register.do">등록 폼</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            게시글 관리
                        </a>
                        <ul class="dropdown-menu dropdown-menu-dark">
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/board/list.do">리스트</a></li>
                            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/board/register.do">등록 폼</a></li>
                        </ul>
                    </li>

                    <li class="d-flex mt-2 nav-item border-top">
                        <%if(session.getAttribute("loginUser")==null){%>
                            <a href="<%=request.getContextPath()%>/user/login.do" class="nav-link me-2">로그인</a>
                            <a href="<%=request.getContextPath()%>/user/register.do" class="nav-link">회원가입</a>
                        <%}else{
                            UserDto loginUser=(UserDto) session.getAttribute("loginUser");
                        %>
                            <a href="" class="nav-link me-2">
                                <%=loginUser.getUserId()%>
                                (<%=loginUser.getName()%>)
                            </a>
                            <a href="<%=request.getContextPath()%>/user/logout.do" class="nav-link">
                                로그아웃
                            </a>
                        <%}%>

                    </li>
                </ul>
            </div>
        </div>
    </div>
</nav>
<%
    Object msg_str=session.getAttribute("msg");
    if(msg_str!=null){
%>
<div class="modal" id="msgModal">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">경고 메세지!</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <%=msg_str%>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<script>
const msgModal=document.getElementById("msgModal");
new bootstrap.Modal(msgModal).show();
</script>
<%
        session.removeAttribute("msg");
    }
%>


