<%@ page pageEncoding="UTF-8" %>
<nav class="d-flex justify-content-center">
    <ul class="pagination mt-4 mb-5" id="replyListPaging">
        <li class="page-item"><a class="page-link <%if(!paging.isPrev()){%>disabled<%}%>" href="?page=1&<%=paging.getQueryString()%>">First</a></li>
        <li class="page-item"><a class="page-link <%if(!paging.isPrev()){%>disabled<%}%>" href="?page=<%=paging.getPrevPage()%>&<%=paging.getQueryString()%>">Prev</a></li>
        <%for(int i=paging.getStartPage(); i<=paging.getEndPage(); i++){%>
            <li class="page-item <%if(paging.getPage()==i){%>active<%}%>" >
                <a class="page-link" href="?page=<%=i%>&<%=paging.getQueryString()%>">
                    <%=i%>
                </a>
            </li>
        <%}%>
        <li class="page-item"><a class="page-link <%if(!paging.isNext()){%>disabled<%}%>" href="?page=<%=paging.getNextPage()%>&<%=paging.getQueryString()%>">Next</a></li>
        <li class="page-item"><a class="page-link <%if(!paging.isNext()){%>disabled<%}%>" href="?page=<%=paging.getEndPage()%>&<%=paging.getQueryString()%>">Last</a></li>
    </ul>
</nav>
<script>
    function  init(){
        const replyListPaging=document.getElementById("replyListPaging");
        const replyList=document.getElementById("replyList");
        const links=replyListPaging.querySelectorAll("a.page-link");

        links.forEach((link)=>{
            link.addEventListener("click",async (e)=>{
                e.preventDefault(); //a태그의 이벤트 막기 (ajax로 동작하게 하기 위해)
                let href=link.href;
                let queryString=href.split("?")[1];
                let ajaxUrl="<%=request.getContextPath()%>/reply/list.do?"+queryString; //리플 리스트 불러오기
                const resp=await fetch(ajaxUrl);
                let text=await resp.text();
                replyList.innerHTML=text;
                init(); //불러온 리플 리스트의 페이징 Node 초기화 하기
            });
        });
    }
    init();
</script>