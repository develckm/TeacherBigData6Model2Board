package com.bd6.board.controller.user;

import com.bd6.board.bigdata6model2board.HelloServlet;
import com.bd6.board.dto.UserDto;
import com.bd6.board.service.UserService;
import com.bd6.board.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login.do")
public class UserLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/user/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인 액션
        String userId=req.getParameter("userId");
        String pw=req.getParameter("pw");
        System.out.println(userId);
        System.out.println(pw);
        UserService userService=null;
        UserDto loginUser=null;
        try {
            userService=new UserServiceImp();
            loginUser=userService.login(userId,pw);
        }catch (Exception e){
            e.printStackTrace();
        }
        //로그인 성공 main or 요청했던 전 페이지 session.reqUrl
        if(loginUser!=null){
            HttpSession session =req.getSession();
            session.setAttribute("loginUser",loginUser);
            Object reqUrl=session.getAttribute("reqUrl");
            if(reqUrl==null){
                resp.sendRedirect(req.getContextPath()+"/");
            }else{
                session.removeAttribute("reqUrl");
                resp.sendRedirect((String) reqUrl);
            }
        }else{
            resp.sendRedirect("login.do");
        }
    }
}
