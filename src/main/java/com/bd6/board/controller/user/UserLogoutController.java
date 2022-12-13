package com.bd6.board.controller.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/logout.do")
public class UserLogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        //session.invalidate(); //세션 객체를 만료시켜서 loginUser 삭제
        session.removeAttribute("loginUser"); //loginUser 삭제
        resp.sendRedirect(req.getContextPath()+"/");


    }
}
