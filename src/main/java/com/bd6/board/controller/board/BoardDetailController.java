package com.bd6.board.controller.board;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.service.BoardService;
import com.bd6.board.service.BoardServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/detail.do")
public class BoardDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String boardNo_str=req.getParameter("boardNo");
        BoardDto board=null;
        BoardService boardService=null;
        try {
            int boardNo=Integer.parseInt(boardNo_str);
            boardService=new BoardServiceImp();
            board=boardService.detail(boardNo);
        }catch (NumberFormatException e){
            e.printStackTrace();
            resp.sendError(400);
            return;
        } catch (Exception e){
            e.printStackTrace();
        }
        if(board!=null){
            req.setAttribute("board",board);
            req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("list.do");
        }
    }
}
