package com.bd6.board.dao;

import com.bd6.board.dto.BoardImgDto;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//Test 메소드 실행순서를 어떻게 하겠다.. 메소드이름, Order(**), 메소드 이름에 순서를....
class BoardImgDaoImpTest {
    static BoardImgDao boardImgDao;
    @BeforeAll
    static  void init() throws Exception {
        boardImgDao=new BoardImgDaoImp();
    }
    @Order(1)
    @Test
    void insert() throws Exception{
        BoardImgDto boardImg=new BoardImgDto();
        boardImg.setBoardNo(1);
        boardImg.setImgPath("a.jpeg");
        System.out.println(boardImgDao.insert(boardImg));
        boardImg=new BoardImgDto();
        boardImg.setBoardNo(1);
        boardImg.setImgPath("b.jpeg");
        System.out.println(boardImgDao.insert(boardImg));
        boardImg=new BoardImgDto();
        boardImg.setBoardNo(1);
        boardImg.setImgPath("c.jpeg");
        System.out.println(boardImgDao.insert(boardImg));
    }
    @Test
    void findById() throws Exception{
        System.out.println(boardImgDao.findById(31));
    }
    @Order(2)
    @Test
    void findByBoardNo() throws Exception {
        System.out.println(boardImgDao.findByBoardNo(1));
    }
    @Order(3)
    @Test
    void deleteById() throws Exception {
        Connection conn=SpringBoardConn.getConn();
        String sql="SELECT LAST_INSERT_ID() as id"; //마지막에 등록된 Img
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            int id=rs.getInt("id");
            System.out.println("삭제될 아이디 :"+id);
            System.out.println(boardImgDao.deleteById(id));
        }
    }
    @Order(4)
    @Test
    void deleteByBoard() throws Exception {
        System.out.println(boardImgDao.deleteByBoard(1));
    }
}