<%@page pageEncoding="UTF-8" %>
<%if( replyList!=null ){%>
<div class="list-group">
    <%for (ReplyDto reply : replyList ){%>
    <div href="#" class="list-group-item py-4">
        <div class="d-flex w-100 justify-content-between">
            <h5><%=reply.getTitle()%></h5>
            <small><%=reply.getPostTime()%></small>
        </div>
        <div class="my-2 d-flex justify-content-between">
            <div>
                <%@include file="prefer.jsp"%>
            </div>
            <div>
                <small class="pe-3">
                    글번호 : <%=reply.getReplyNo()%>
                </small>
                <small>
                    작성자 : <%=reply.getUserId()%>
                </small>
            </div>
        </div>
        <div><%=reply.getContents()%></div>
        <%List<ReplyDto> rrList= reply.getReplyList();%>
        <%if(rrList!=null){%>
            <h5 class="my-3">
                <a href="#rrList<%=reply.getReplyNo()%>" data-bs-toggle="collapse">
                    <%=reply.getReplyNo()%>.
                    대댓글
                    (<%=rrList.size()%>)
                </a>
            </h5>
            <div id="rrList<%=reply.getReplyNo()%>" class="collapse">
                <%@include file="rr_list.jsp"%>
            </div>
        <%}%>
    </div>
    <%}%>
    <%@include file="pagingNav.jsp"%>
</div>
<%}%>
