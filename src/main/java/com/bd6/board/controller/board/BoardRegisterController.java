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
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet("/board/register.do")
public class BoardRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인 한사람만 등록폼을 볼 수 있고
        //로그인 하지 않은 사람은  400 or 로그인으로 이동!
        HttpSession session=req.getSession();
        Object loginUser_obj=session.getAttribute("loginUser");
        if(loginUser_obj!=null){
            req.getRequestDispatcher("/WEB-INF/views/board/register.jsp").forward(req,resp);
        }else{
            //resp.sendError(400);
            session.setAttribute("msg","게시글 등록은 로그인해야 가능합니다!");
            resp.sendRedirect(req.getContextPath()+"/user/login.do");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        HttpSession session=req.getSession();
        Object loginUser_obj=session.getAttribute("loginUser");
        if(loginUser_obj==null){ //로그인 하지 않은 사람은 등록을 할 수 없다!! 400 or 다시 등록 폼
            session.setAttribute("msg","로그인 한 사람만 게시글을 등록할 수 있습니다.");
            resp.sendRedirect("login.do");
            return;
        }

        //form data가 blob일때만 사용가능(multipart/form-data)
        //MultipartRequest : cos의 클래스로 blob 데이터를 처리해준다.(파일이면 저장, 문자열인 인코딩 처리)
        //1.파일크기 제한 (MultipartRequest 처리)
        //2.파일이름설정 (FileRenamePolicy 객체를 재정의 , 저장된 파일의 이름을 바꾼다.)
        //3.파일 형식을 확인해서서 이미지가 아닌 것은 삭제!
        String imgPath=req.getServletContext().getRealPath("/public/img"); //배포된 프로젝트의 물리적인 위치(target)
        //target : war를 배포하는 경로로 매번 초기화된다. (img 업로드를 하면 안된다.)
        imgPath= SpringBoardConn.IMG_PATH;
        // 톰캣 구성편집 > Tomcat 인스턴스에 구성된 어플리케이션 배포 (체크) : 정적파일은 war로 배포하기 전 어플의 소스를 사용
        int fileSize=1024*1024*1; //1mb;
        //DefaultFileRenamePolicy : 동일한 이름의 파일이 존재하면 (1~2~10) 수를 더해서 중복을 막는다.
        int register=0; //게시글 저장성공=1 (+이미지저장)
        BoardDto board=null;
        try{
            MultipartRequest multiReq=new MultipartRequest(req,imgPath,fileSize,"UTF-8",new DefaultFileRenamePolicy());
            String title=multiReq.getParameter("title");
            String contents=multiReq.getParameter("contents");
            String userId=multiReq.getParameter("userId");

            board=new BoardDto();
            board.setTitle(title);
            board.setContents(contents);
            board.setUserId(userId);
            List<BoardImgDto> boardImgList=new ArrayList<BoardImgDto>();
            Enumeration<String> fileNames =multiReq.getFileNames();
            BoardService boardService=null;
            while (fileNames.hasMoreElements()){
                String fileName=fileNames.nextElement();
                File imgFile=multiReq.getFile(fileName);
                if(imgFile!=null){
                    String []contentsTypes=multiReq.getContentType(fileName).split("/");//"image/jpeg"
                    if (!contentsTypes[0].equals("image")){
                        System.out.println("이지가 아닡 파일 삭제 :"+imgFile.delete());
                    }else{
                        int random=(int)(Math.random()*10000);
                        //0.123124234324523452345 =>1231.24234324523452345 => 1231
                        String fileRename="board_"+System.currentTimeMillis()+"_"+random+"."+contentsTypes[1];
                        imgFile.renameTo(new File(imgPath+"/"+fileRename));
                        BoardImgDto boardImg=new BoardImgDto();
                        boardImg.setImgPath(fileRename);
                        boardImgList.add(boardImg);
                    }
                }
            }//img 저장 끝
            board.setBoardImgList(boardImgList);
            boardService=new BoardServiceImp();
            register=boardService.register(board);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("저장 :"+register);
        if(register>0){ //성공시 리스트
            resp.sendRedirect("detail.do?boardNo="+board.getBoardNo());
        }else { //실패시 다시 폼으로 이동
            resp.sendRedirect("register.do");
        }
    }
}
