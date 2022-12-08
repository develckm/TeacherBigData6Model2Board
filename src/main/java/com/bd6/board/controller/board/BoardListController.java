package com.bd6.board.controller.board;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;
import com.bd6.board.service.BoardService;
import com.bd6.board.service.BoardServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/list.do")
public class BoardListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page_str=req.getParameter("page");
        String rows_str=req.getParameter("rows");
        String order=req.getParameter("order");
        String direct=req.getParameter("direct");
        List<BoardDto> boardList=null;
        PagingDto paging=null;
        BoardService boardService=null;
        try {
            int page=(page_str!=null)?Integer.parseInt(page_str):1;
            int rows=(rows_str!=null)?Integer.parseInt(rows_str):7;
            order=(order!=null)?order:"board_no";
            direct=(direct!=null)?direct:"DESC";
            paging=new PagingDto(page,rows,order,direct);
            boardService=new BoardServiceImp();
            boardList=boardService.list(paging);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(boardList==null){
            resp.sendRedirect(req.getContextPath()+"/");
        }else{
            req.setAttribute("boardList",boardList);
            req.setAttribute("paging",paging);
            req.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(req,resp);
        }
    }
}
