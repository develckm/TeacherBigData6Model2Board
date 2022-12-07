package com.bd6.board.dao;

import java.sql.*;

public class SpringBoardConn {
    //싱글톤패턴으로 db 접속
    private static Connection conn;
    private static String url="jdbc:mysql://localhost:3306/SPRING_BOARD";
    private static String user="board_user";
    private static String pw="1234";
    public  static Connection getConn() throws Exception {
        if(conn==null || conn.isClosed()){
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(url,user,pw);
        }
        return  conn;
    }
}

