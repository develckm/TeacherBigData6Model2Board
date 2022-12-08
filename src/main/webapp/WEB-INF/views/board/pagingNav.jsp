<%@ page pageEncoding="UTF-8" %>
<nav class="d-flex justify-content-center">
    <ul class="pagination my-4">
        <li class="page-item"><a class="page-link" href="#">Prev</a></li>
        <%for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){%>
            <li class="page-item <%if(paging.getPage()==i){%>active<%}%>" >
                <a class="page-link" href="?page=<%=i%>">
                    <%=i%>
                </a>
            </li>
        <%}%>
        <li class="page-item"><a class="page-link" href="#">Next</a></li>
    </ul>
</nav>
