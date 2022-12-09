package com.bd6.board.controller.reply;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;
import com.bd6.board.service.BoardService;
import com.bd6.board.service.ReplyService;
import com.bd6.board.service.ReplyServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/reply/list.do")
public class ReplyListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page_str=req.getParameter("page");
        String rows_str=req.getParameter("rows");
        String order=req.getParameter("order");
        String direct=req.getParameter("direct");
        String boardNo_str=req.getParameter("boardNo");
        PagingDto paging=null;
        ReplyService replyService=null;
        List<ReplyDto> replyList=null;
        try {
            int page=(page_str!=null)?Integer.parseInt(page_str):1;
            int rows=(rows_str!=null)?Integer.parseInt(rows_str):5;
            order=(order!=null)?order:"reply_no";
            direct=(direct!=null)?direct:"DESC";
            paging=new PagingDto(page,rows,order,direct);
            paging.setQueryString(req.getParameterMap());
            int boardNo=Integer.parseInt(boardNo_str);

            replyService=new ReplyServiceImp();
            replyList=replyService.boardDetailList(paging,boardNo);

        }catch (Exception e){
            e.printStackTrace();
            resp.sendError(500);
        }
        if(replyList==null){
            resp.setStatus(204); //요청을 잘 처리했지만 레코드가 없음
        }else{
            System.out.println(replyList);
            req.setAttribute("replyList",replyList);
            req.setAttribute("paging",paging);
            req.getRequestDispatcher("/WEB-INF/views/reply/ajaxList.jsp").forward(req,resp);
        }
    }
}
