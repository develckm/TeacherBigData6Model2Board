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

                </ul>
                <form class="d-flex mt-3" role="search">
                </form>
            </div>
        </div>
    </div>
</nav>
