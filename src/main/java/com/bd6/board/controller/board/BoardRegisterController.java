package com.bd6.board.controller.board;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet("/board/register.do")
public class BoardRegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/board/register.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //form data가 blob일때만 사용가능(multipart/form-data)
        //MultipartRequest : cos의 클래스로 blob 데이터를 처리해준다.(파일이면 저장, 문자열인 인코딩 처리)
        //1.파일크기 제한 (MultipartRequest 처리)
        //2.파일이름설정 (FileRenamePolicy 객체를 재정의 , 저장된 파일의 이름을 바꾼다.)
        //3.파일 형식을 확인해서서 이미지가 아닌 것은 삭제!
        String imgPath=req.getServletContext().getRealPath("/public/img"); //배포된 프로젝트의 물리적인 위치
        //imgPath="/Users/choegyeongmin/intellij_study_workspace/TeacherBigData6Model2Board/src/main/webapp/public/img";
        System.out.println(imgPath);
        int fileSize=1024*1024*1; //1mb;
        //DefaultFileRenamePolicy : 동일한 이름의 파일이 존재하면 (1~2~10) 수를 더해서 중복을 막는다.
        try{
            MultipartRequest multiReq=new MultipartRequest(req,imgPath,fileSize,"UTF-8",new DefaultFileRenamePolicy());
            String title=multiReq.getParameter("title");
            File imgFile1=multiReq.getFile("imgFile1");
            File imgFile2=multiReq.getFile("imgFile2");
            File imgFile3=multiReq.getFile("imgFile3");
            File imgFile4=multiReq.getFile("imgFile4");
            System.out.println(title);
            System.out.println(imgFile1);
            System.out.println(imgFile2);
            System.out.println(imgFile3);
            System.out.println(imgFile4);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
