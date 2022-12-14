package com.bd6.board.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
//dd(web.xml) 배포서술자 : 웹앱서버의 설정을 개발자가 해서 배포하는 것 (동적리소스 위치,웰컴파일리시트,정적리소스 종류,세션의 만료시간,필터설정 .....)
@WebFilter("*.do")
public class ParamEncondingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        //System.out.println("ParamEncondingFilter입니다.");
        chain.doFilter(request,response); //원래 요청으로 이동
    }
}
