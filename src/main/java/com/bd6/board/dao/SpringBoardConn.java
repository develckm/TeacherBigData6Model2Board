package com.bd6.board.dao;

import java.sql.*;

public class SpringBoardConn {
    //싱글톤패턴으로 db 접속
    private static Connection conn;
    private static String url="jdbc:mysql://localhost:3306/SPRING_BOARD";
    private static String user="board_user";
    private static String pw="1234";
    public final static String IMG_PATH="/Users/choegyeongmin/intellij_study_workspace/TeacherBigData6Model2Board/src/main/webapp/public/img";
    public  static Connection getConn() throws Exception {
        if(conn==null || conn.isClosed()){
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pw);
        }
        return  conn;
    }
    //last_insert_id() 는 오토인크리먼트로 마지막으로 저장된 Id를 반환
    public static int getLastInsertId() throws Exception{
        int id=0;
        String sql="SELECT LAST_INSERT_ID() as id";
        Statement stmt=conn.createStatement();
        ResultSet rs=stmt.executeQuery(sql);
        if(rs.next()){
            id=rs.getInt("id");
        }
        return  id; //저장된 내역이 없으면 0
    }
}

