package com.bd6.board.dao;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImp implements BoardDao{
    Connection conn;
    PreparedStatement pstmt;
    ResultSet rs;

    public BoardDaoImp() throws Exception {
        conn=SpringBoardConn.getConn();
    }

    @Override
    public List<BoardDto> findAll() throws Exception {
        List<BoardDto> boardList=null;
        String sql="SELECT * FROM BOARD";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        boardList=new ArrayList<BoardDto>();
        while(rs.next()){
            BoardDto board=rsParseBoardDto(rs);
            boardList.add(board);
        }
        return boardList;
    }

    @Override
    public List<BoardDto> findPaging(PagingDto paging) throws Exception {
        List<BoardDto> boardList=null;
        String sql="SELECT * FROM BOARD ORDER BY board_no DESC LIMIT ?,?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,paging.getStartRow());
        pstmt.setInt(2,paging.getRows());
        rs=pstmt.executeQuery();
        boardList=new ArrayList<BoardDto>();
        while(rs.next()){
            BoardDto board=rsParseBoardDto(rs);
            boardList.add(board);
        }
        return boardList;
    }
    public BoardDto rsParseBoardDto(ResultSet rs) throws  Exception{
        BoardDto board=new BoardDto();
        board.setBoardNo(rs.getInt("board_no"));
        board.setContents(rs.getString("contents"));
        board.setTitle(rs.getString("title"));
        board.setUserId(rs.getString("user_id"));
        board.setViews(rs.getInt("views"));
        board.setPostTime(rs.getTime("post_time"));
        return  board;
    }

    @Override
    public int count(PagingDto paging) throws Exception {
        return 0;
    }

    @Override
    public BoardDto findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public int updateById(BoardDto dto) throws Exception {
        return 0;
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        return 0;
    }
}
