package com.bd6.board.controller.user;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.UserDto;
import com.bd6.board.service.UserService;
import com.bd6.board.service.UserServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/list.do")
public class UserListController  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page_str=req.getParameter("page");
        String rows_str=req.getParameter("rows");
        String orderField=req.getParameter("order");
        String direct=req.getParameter("direct");
        UserService userService=null;
        Map<String,Boolean> testUserField=new HashMap<String,Boolean>();
        testUserField.put("user_id",true);
        testUserField.put("name",true);
        testUserField.put("phone",true);
        testUserField.put("birth",true);
        testUserField.put("signup",true);
        try {
            int page=(page_str!=null)?Integer.parseInt(page_str):1;
            int rows=(rows_str!=null)?Integer.parseInt(rows_str):10;
            orderField=(orderField==null||testUserField.get(orderField)==null)?"user_id":orderField;
            direct=(direct==null||!(direct.equals("ASC")||direct.equals("DESC")))?"DESC":direct;
            PagingDto paging=new PagingDto(page,rows,orderField,direct);
            System.out.println(paging);
            userService=new UserServiceImp();
            List<UserDto> userList=userService.list(paging);
            System.out.println(userList);
            System.out.println(paging);
            req.setAttribute("userList",userList);
            req.setAttribute("paging",paging);

            req.getRequestDispatcher("/WEB-INF/views/user/list.jsp").forward(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendError(500);
        }
    }
}
