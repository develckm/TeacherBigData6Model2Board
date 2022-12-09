<%@page pageEncoding="UTF-8" %>
<%if( rrrList!=null ){%>
<div class="list-group">
    <%for (ReplyDto rrr : rrrList ){%>
    <div href="#" class="list-group-item py-4">
        <div class="d-flex w-100 justify-content-between">
            <h5><%=rrr.getTitle()%></h5>
            <small><%=rrr.getPostTime()%></small>
        </div>
        <p class="d-flex justify-content-end">
            <small class="pe-3">
                글번호 : <%=rrr.getReplyNo()%>
            </small>
            <small class="pe-3">
                좋아요 : 5
            </small>
            <small class="pe-3">
                싫어요 : 3
            </small>
            <small>
                작성자 : <%=rrr.getUserId()%>
            </small>
        </p>
        <div><%=rrr.getContents()%></div>
        <div>
        </div>
    </div>
    <%}%>
</div>
<%}%>
