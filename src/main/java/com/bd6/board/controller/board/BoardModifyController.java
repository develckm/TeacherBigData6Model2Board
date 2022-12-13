package com.bd6.board.controller.board;

import com.bd6.board.dao.SpringBoardConn;
import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.BoardImgDto;
import com.bd6.board.service.BoardService;
import com.bd6.board.service.BoardServiceImp;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/board/modify.do")
public class BoardModifyController extends HttpServlet {
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
            req.getRequestDispatcher("/WEB-INF/views/board/modify.jsp").forward(req,resp);
        }else{
            resp.sendRedirect("list.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String imgPath=req.getServletContext().getRealPath("/public/img"); //배포된 프로젝트의 물리적인 위치(target)
        imgPath= SpringBoardConn.IMG_PATH;
        int fileSize=1024*1024*1; //1mb;
        int modify =0; //게시글 저장성공=1 (+이미지저장)
        BoardDto board=null;
        String boardNo_str=null;
        List<File> imgFileList=new ArrayList<File>(); //저장된 파일을 갖는 리스트
        try{
            MultipartRequest multiReq=new MultipartRequest(req,imgPath,fileSize,"UTF-8",new DefaultFileRenamePolicy());
            String title=multiReq.getParameter("title");
            String contents=multiReq.getParameter("contents");
            String userId=multiReq.getParameter("userId");
            boardNo_str=multiReq.getParameter("boardNo");
            String[] delBoardImgNos_str =multiReq.getParameterValues("delBoardImgNo");
            //?delBoardImgNo=1&delBoardImgNo=2&delBoardImgNo=10 .....

            int[] delBoardImgNos=null;
            if(delBoardImgNos_str!=null){
                delBoardImgNos=Arrays.stream(delBoardImgNos_str)
                        .mapToInt(Integer::parseInt)
                        .toArray(); //{3,10,11}=>이미지 삭제 번호
            }
            board=new BoardDto();
            board.setTitle(title);
            board.setContents(contents);
            board.setUserId(userId);
            System.out.println(Arrays.toString(delBoardImgNos_str));
            System.out.println(board);
            List<BoardImgDto> boardImgList=new ArrayList<BoardImgDto>();
            Enumeration<String> fileNames =multiReq.getFileNames();
            BoardService boardService=null;
            while (fileNames.hasMoreElements()){
                String fileName=fileNames.nextElement();
                File imgFile=multiReq.getFile(fileName);
                if(imgFile!=null){
                    String []contentsTypes=multiReq.getContentType(fileName).split("/");//"image/jpeg"
                    if (!contentsTypes[0].equals("image")){
                        System.out.println("이미지가 아닡 파일 삭제 :"+imgFile.delete());
                    }else{
                        int random=(int)(Math.random()*10000);
                        //0.123124234324523452345 =>1231.24234324523452345 => 1231
                        String fileRename="board_"+System.currentTimeMillis()+"_"+random+"."+contentsTypes[1];
                        File renameFile=new File(imgPath+"/"+fileRename);
                        imgFile.renameTo(renameFile);
                        imgFileList.add(renameFile);
                        BoardImgDto boardImg=new BoardImgDto();
                        boardImg.setImgPath(fileRename);
                        boardImgList.add(boardImg);
                    }
                }
            }//img 저장 끝
            board.setBoardImgList(boardImgList);
            board.setBoardNo(Integer.parseInt(boardNo_str));
            boardService=new BoardServiceImp();
            modify =boardService.modify(board,delBoardImgNos);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("수정 :"+ modify);
        if(modify >0){ //성공시 리스트
            resp.sendRedirect("detail.do?boardNo="+boardNo_str);
        }else { //실패시 다시 폼으로 이동
            imgFileList.stream().forEach(File::delete);  //저장했던 이미지 삭제
            resp.sendRedirect("modify.do?boardNo="+boardNo_str);
        }
    }
}
