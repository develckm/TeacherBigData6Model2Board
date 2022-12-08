package com.bd6.board.controller.user;

import com.bd6.board.service.UserService;
import com.bd6.board.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/idCheck.do")
public class UserIdCheckController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId=req.getParameter("userId");
        if (userId==null){
            resp.sendError(400);
            return;
        }
        UserService userService=null;
        int idCheck=0;
        try{
            userService=new UserServiceImp();
            idCheck=userService.idCheck(userId);
        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(500);
            return;
        }
        resp.setHeader("Content-Type","application/json;charset=utf-8");
        resp.getWriter().append("{\"idCheck\":"+idCheck+"}");
    }
}
