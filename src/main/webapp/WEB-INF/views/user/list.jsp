<%@ page import="com.bd6.board.dto.UserDto" %>
<%@ page import="com.bd6.board.dto.PagingDto" %>
<%@ page import="lombok.Data" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>유저 관리 리스트</title>
</head>
<body>
<%@include file="/headerNav.jsp" %>
<main class="container">
    <h1 class="mt-5">유저 리스트</h1>
    <table class="table">
        <thead>
        <tr>
<%!
static class OrderField {
    String name;
    String nameText;
    String direct="DESC";
    String directText="▼";//"▲"
    String className="link-secondary";

    boolean selected;
    public OrderField(String name, String nameText, boolean selected, String direct){
        this.name=name;
        this.nameText=nameText;
        if(selected){
            this.className="link-primary";
            if(direct==null || direct.equals("DESC")){
                this.direct="ASC";
                this.directText="▼";
            }else{
                this.direct="DESC";
                this.directText="▲";
            }
        }
    }
}%>
<%
    String orderParam=request.getParameter("order");
    String directParam=request.getParameter("direct");
    if(orderParam==null)orderParam="user_id";
    List<OrderField> feildList=new ArrayList<OrderField>();
    feildList.add(new OrderField("user_id", "아이디",orderParam.equals("user_id"),directParam));
    feildList.add(new OrderField("name", "이름",orderParam.equals("name"),directParam));
    feildList.add(new OrderField("phone", "폰 번호",orderParam.equals("phone"),directParam));
    feildList.add(new OrderField("birth", "생일",orderParam.equals("birth"),directParam));
    feildList.add(new OrderField("signup", "가입일",orderParam.equals("signup"),directParam));
%>

            <% for(OrderField f: feildList) {%>
            <th>
                <a class="<%=f.className%>" href="?order=<%=f.name%>&direct=<%=f.direct%>">
                    <%=f.nameText%>
                    <%=f.directText%>
                </a>
            </th>
            <%}%>
        </tr>
        </thead>
        <tbody>
        <%
            List<UserDto> userList = (List<UserDto>) request.getAttribute("userList");
            for (UserDto user : userList) {
        %>
        <tr>
            <td><%=user.getUserId()%>
            </td>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getPhone()%>
            </td>
            <td><%=user.getBirth()%>
            </td>
            <td><%=user.getSignup()%>
            </td>
        </tr>

        <%}%>
        </tbody>
    </table>
    <%
        PagingDto paging = (PagingDto) request.getAttribute("paging");

        StringBuffer url = request.getRequestURL();
        String queryString = request.getQueryString();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> names = parameterMap.keySet();
        String newQueryString = "";
        for (String name : names) {
            if (!name.equals("page")) {
                String[] values = parameterMap.get(name);
                for (String v : values) {
                    newQueryString += name + "=" + v + "&";
                }
            }
        }
    %>
    <ul>
        <li>request.getRequestURL():<%=url%>
        </li>
        <li>request.getQueryString():<%=queryString%>
        </li>
        <li>request.parameterMap():<%=parameterMap%>
        </li>
        <li>새롭게 만든 쿼리 스트링 :<%=newQueryString%>
        </li>

    </ul>
    <nav class="mt-5 d-flex justify-content-center">
        <ul class="pagination">
            <li class="page-item">
                <a class="page-link" href="?page=1&<%=newQueryString%>">
                    First
                </a>
            </li>

            <li class="page-item">
                <a class="page-link" href="?page=<%=paging.getPage()-1%>&<%=newQueryString%>">
                    Prev
                </a>
            </li>
            <%for (int i = paging.getStartPage(); i <= paging.getEndPage(); i++) {%>
            <li class="page-item <%if(i==paging.getPage()){%>active<%}%>">
                <a class="page-link" href="?page=<%=i%>&<%=newQueryString%>">
                    <%=i%>
                </a>
            </li>
            <%}%>
            <li class="page-item">
                <a class="page-link <%if(!paging.isNext()){%>disabled<%}%> "
                   href="?page=<%=paging.getPage()+1%>&<%=newQueryString%>">
                    Next
                </a>
            </li>
            <li class="page-item">
                <a class="page-link" href="?page=<%=paging.getTotalPages()%>&<%=newQueryString%>">
                    Last
                </a>
            </li>

        </ul>
    </nav>

</main>
</body>
</html>
