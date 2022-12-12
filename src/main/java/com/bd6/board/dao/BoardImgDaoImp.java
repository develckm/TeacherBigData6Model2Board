package com.bd6.board.dao;

import com.bd6.board.dto.BoardImgDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardImgDaoImp implements  BoardImgDao{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public BoardImgDaoImp() throws Exception {
        conn=SpringBoardConn.getConn();
    }
    @Override
    public List<BoardImgDto> findByBoardNo(int boardNo) throws Exception {
        List<BoardImgDto> boardImgList=null;
        String sql="SELECT * FROM BOARD_IMG WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardNo);
        rs=pstmt.executeQuery();
        int i=0;
        while(rs.next()){
            if(i++==0) boardImgList=new ArrayList<BoardImgDto>();
            BoardImgDto boardImg=new BoardImgDto();
            boardImg.setBoardImgNo(rs.getInt("board_img_no"));
            boardImg.setBoardNo(rs.getInt("board_no"));
            boardImg.setImgPath(rs.getString("img_path"));
            boardImgList.add(boardImg);
        }
        return boardImgList;
    }

    @Override
    public int deleteByBoard(int boardNo) throws Exception {
        int delete=0;
        String sql="DELETE FROM BOARD_IMG WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardNo);
        delete=pstmt.executeUpdate();
        return delete;
    }

    @Override
    public int deleteById(int boardImgNo) throws Exception {
        int delete=0;
        String sql="DELETE FROM BOARD_IMG WHERE board_img_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardImgNo);
        delete=pstmt.executeUpdate();
        return delete;
    }

    @Override
    public int insert(BoardImgDto boardImg) throws Exception {
        int insert=0;
        String sql="INSERT INTO BOARD_IMG (board_no, img_path) VALUES (?,?)";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardImg.getBoardNo());
        pstmt.setString(2,boardImg.getImgPath());
        insert=pstmt.executeUpdate();
        return insert;
    }
}
