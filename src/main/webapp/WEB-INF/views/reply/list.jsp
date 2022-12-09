<%@page pageEncoding="UTF-8" %>
<%if( replyList!=null ){%>
<div class="list-group">
    <%for (ReplyDto reply : replyList ){%>
    <div href="#" class="list-group-item py-4">
        <div class="d-flex w-100 justify-content-between">
            <h5><%=reply.getTitle()%></h5>
            <small><%=reply.getPostTime()%></small>
        </div>
        <p class="d-flex justify-content-end">
            <small class="pe-3">
                글번호 : <%=reply.getReplyNo()%>
            </small>
            <small class="pe-3">
                좋아요 : 5
            </small>
            <small class="pe-3">
                싫어요 : 3
            </small>
            <small>
                작성자 : <%=reply.getUserId()%>
            </small>
        </p>
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
</div>
<%}%>
