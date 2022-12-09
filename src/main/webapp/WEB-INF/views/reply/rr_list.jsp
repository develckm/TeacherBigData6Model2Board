<%@page pageEncoding="UTF-8" %>
<%if( rrList!=null ){%>
<div class="list-group">
    <%for (ReplyDto rr : rrList ){%>
    <div href="#" class="list-group-item py-4">
        <div class="d-flex w-100 justify-content-between">
            <h5><%=rr.getTitle()%></h5>
            <small><%=rr.getPostTime()%></small>
        </div>
        <p class="d-flex justify-content-end">
            <small class="pe-3">
                글번호 : <%=rr.getReplyNo()%>
            </small>
            <small class="pe-3">
                좋아요 : 5
            </small>
            <small class="pe-3">
                싫어요 : 3
            </small>
            <small>
                작성자 : <%=rr.getUserId()%>
            </small>
        </p>
        <div><%=rr.getContents()%></div>
        <%List<ReplyDto> rrrList=rr.getReplyList();%>
        <%if(rrrList!=null){%>
        <div>
            <h5 class="my-3">
                <a href="#rrrList<%=rr.getReplyNo()%>" data-bs-toggle="collapse">
                    <%=rr.getReplyNo()%>. 대대댓글
                    (<%=rrrList.size()%>)
                </a>
            </h5>
            <div id="rrrList<%=rr.getReplyNo()%>" class="collapse">
                <%@include file="rrr_list.jsp"%>
            </div>
        </div>
        <%}%>
    </div>
    <%}%>
</div>
<%}%>
