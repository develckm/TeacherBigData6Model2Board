<%@ page pageEncoding="UTF-8" %>
<nav class="d-flex justify-content-center">
    <ul class="pagination my-4">
        <li class="page-item"><a class="page-link <%if(!paging.isPrev()){%>disabled<%}%>" href="?page=1">First</a></li>
        <li class="page-item"><a class="page-link <%if(!paging.isPrev()){%>disabled<%}%>" href="?page=<%=paging.getPrevPage()%>">Prev</a></li>
        <%for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){%>
            <li class="page-item <%if(paging.getPage()==i){%>active<%}%>" >
                <a class="page-link" href="?page=<%=i%>">
                    <%=i%>
                </a>
            </li>
        <%}%>
        <li class="page-item"><a class="page-link <%if(!paging.isNext()){%>disabled<%}%>" href="?page=<%=paging.getNextPage()%>">Next</a></li>
        <li class="page-item"><a class="page-link <%if(!paging.isNext()){%>disabled<%}%>" href="?page=<%=paging.getEndPage()%>">Last</a></li>

    </ul>
</nav>
