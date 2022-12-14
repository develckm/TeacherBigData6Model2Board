package com.bd6.board.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(urlPatterns = {  "/*/register.do",
                            "/*/modify.do",
                            "/*/delete.do",
                            "/board/register.do",
                            "/board/modify.do",
                            "/board/delete.do"} )
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("등록,등록폼,수정,수정폼,삭제를 요청했습니다!");
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp=(HttpServletResponse) response;
        //url : 서버주소를 포함하는 리소스 주소
        //uri : 서버내부의 리소스 위치
        String reqUrl=req.getRequestURI()+"?"+req.getQueryString();
        HttpSession session=req.getSession();
        Object loginUser_obj=session.getAttribute("loginUser");
        if(loginUser_obj!=null){//로그인 된 사람은 리소스 요청 가능
            chain.doFilter(req,response);
        }else{
            if(req.getMethod().equals("GET")){
                session.setAttribute("reqUrl",reqUrl);
            }
            session.setAttribute("msg","로그인 필터에서 체크 (수정 등록 삭제는 로그인시 가능)");
            resp.sendRedirect(req.getContextPath()+"/user/login.do");
        }
    }
}
