package com.bd6.board.dao;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.BoardImgDto;
import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;

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
        String sql="SELECT * FROM BOARD ORDER BY "+paging.getOrderField()+" "+paging.getDirect()+" LIMIT ?,?";
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
        int count=0;
        String sql="SELECT COUNT(*) cnt FROM BOARD";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next()){
            count=rs.getInt("cnt");
        }
        return count;
    }

    @Override
    public BoardDto findById(Integer id) throws Exception {
        BoardDto board=null;
        String sql="SELECT * FROM BOARD WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        rs=pstmt.executeQuery();
        while(rs.next()){
            board = rsParseBoardDto(rs);
        }
        return board;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        int delete=0;
        String sql="DELETE FROM BOARD WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        delete=pstmt.executeUpdate();
        return delete;
    }

    @Override
    public int updateById(BoardDto dto) throws Exception {
        int update=0;
        String sql="UPDATE BOARD SET title=?,contents=? WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,dto.getTitle());
        pstmt.setString(2,dto.getContents());
        pstmt.setInt(3,dto.getBoardNo());
        update=pstmt.executeUpdate();
        return update;
    }

    @Override
    public int insert(BoardDto dto) throws Exception {
        int insert=0;
        String sql="INSERT INTO BOARD (title, contents, user_id) VALUES (?,?,?)";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,dto.getTitle());
        pstmt.setString(2,dto.getContents());
        pstmt.setString(3,dto.getUserId());
        insert=pstmt.executeUpdate();
        return insert;
    }

    @Override
    public int updateViews(int boardNo) throws Exception {
        int update=0;
        String sql="UPDATE BOARD SET views=views+1 WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardNo);
        update=pstmt.executeUpdate();
        return update;
    }
}
