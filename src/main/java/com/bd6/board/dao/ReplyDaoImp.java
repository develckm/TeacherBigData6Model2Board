package com.bd6.board.dao;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReplyDaoImp implements ReplyDao{
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public ReplyDaoImp() throws Exception {
        this.conn=SpringBoardConn.getConn();
    }

    @Override
    public List<ReplyDto> findAll() throws Exception {
        return null;
    }

    @Override
    public List<ReplyDto> findPaging(PagingDto paging) throws Exception {
        return null;
    }

    @Override
    public int count(PagingDto paging) throws Exception {
        return 0;
    }

    @Override
    public ReplyDto findById(Integer id) throws Exception {
        return null;
    }

    @Override
    public int deleteById(Integer id) throws Exception {
        return 0;
    }

    @Override
    public int updateById(ReplyDto dto) throws Exception {
        return 0;
    }

    @Override
    public int insert(ReplyDto dto) throws Exception {
        return 0;
    }

    @Override
    public List<ReplyDto> findByBoardNo(PagingDto paging, int boardNo) throws Exception {
        List<ReplyDto> replyList=null;
        String sql="SELECT * FROM REPLY WHERE board_no=? and fk_reply_no is null " +
                "ORDER BY "+paging.getOrderField()+" "+paging.getDirect()+" LIMIT ?,?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardNo);
        pstmt.setInt(2,paging.getStartRow());
        pstmt.setInt(3,paging.getRows());
        rs=pstmt.executeQuery();
        replyList=new ArrayList<ReplyDto>();
        while (rs.next()){
            ReplyDto reply=rsParseReplyDto(rs);
            replyList.add(reply);
        }
        return replyList;
    }

    @Override
    public List<ReplyDto> findByFkReplyNo( int fkReplyNo ) throws Exception {
        List<ReplyDto> replyList=null;
        String sql="SELECT * FROM REPLY WHERE fk_reply_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,fkReplyNo);
        rs=pstmt.executeQuery();
        int i=0;
        while (rs.next()){
            if(i++==0){ //댓글이 한개라도 있으면 만들겠다.
                replyList=new ArrayList<ReplyDto>();
            }
            ReplyDto reply=rsParseReplyDto(rs);
            replyList.add(reply);
        }
        return replyList;
    }

    public ReplyDto rsParseReplyDto(ResultSet rs) throws Exception{
        ReplyDto reply=new ReplyDto();
        reply.setReplyNo(rs.getInt("reply_no"));
        reply.setTitle(rs.getString("title"));
        reply.setContents(rs.getString("contents"));
        reply.setPostTime(rs.getTime("post_time"));
        reply.setImgPath(rs.getString("img_path"));
        reply.setBoardNo(rs.getInt("board_no"));
        reply.setUserId(rs.getString("user_id"));
        reply.setFkReplyNo((Integer) rs.getObject("fk_reply_no"));
        return reply;
    }
    @Override
    public int countByBoard(int boardNo) throws Exception {
        int cnt=0;
        String sql="SELECT COUNT(*) cnt FROM REPLY WHERE board_no=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,boardNo);
        rs=pstmt.executeQuery();
        if (rs.next()){
            cnt=rs.getInt("cnt");
        }
        return cnt;
    }
}
