package com.bd6.board.dao;

import com.bd6.board.dto.BoardDto;
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
        //BoardDto.replyList(List<ReplyDto>)를 만들어서 조인하고 파싱하세요!
        //문제들....
        BoardDto board=null;
        String sql="SELECT b.*,r.title r_title,r.* FROM BOARD  b LEFT JOIN REPLY r USING(board_no) WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,id);
        rs=pstmt.executeQuery();
        int index=0;
        while(rs.next()){
            if(index++==0){
                board=rsParseBoardDto(rs);
                if(rs.getObject("reply_no")!=null){
                    List<ReplyDto> replyList=new ArrayList<>();
                    board.setReplyList(replyList);
                }
            }
            if (rs.getObject("reply_no")!=null){ //댓글이 존재
                ReplyDto reply=new ReplyDto();
                reply.setReplyNo(rs.getInt("reply_no"));
                reply.setBoardNo(rs.getInt("board_no"));
                reply.setFkReplyNo((Integer) rs.getObject("fk_reply_no"));
                reply.setPostTime(rs.getTime("post_time"));
                reply.setTitle(rs.getString("r_title"));
                reply.setContents(rs.getString("contents"));
                reply.setImgPath(rs.getString("img_path"));
                reply.setUserId(rs.getString("user_id"));
                board.getReplyList().add(reply);
            }
        }
        return board;
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
