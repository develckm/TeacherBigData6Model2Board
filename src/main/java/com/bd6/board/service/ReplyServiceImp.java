package com.bd6.board.service;

import com.bd6.board.dao.ReplyDao;
import com.bd6.board.dao.ReplyDaoImp;
import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;

import java.util.List;

public class ReplyServiceImp implements ReplyService{
    private ReplyDao replyDao;

    public ReplyServiceImp() throws Exception {
        replyDao=new ReplyDaoImp();
    }

    @Override
    public List<ReplyDto> list(PagingDto paging) throws Exception {
        return null;
    }

    @Override
    public List<ReplyDto> boardDetailList(PagingDto paging, int boardNo) throws Exception {
        List<ReplyDto> replyList=replyDao.findByBoardNo(paging,boardNo);
        for(ReplyDto reply : replyList){
            List<ReplyDto> rrList=replyDao.findByFkReplyNo(reply.getReplyNo());
            reply.setReplyList(rrList);
            if(rrList!=null){
                for(ReplyDto rr : rrList){
                    List<ReplyDto> rrrList=replyDao.findByFkReplyNo(rr.getReplyNo());
                    rr.setReplyList(rrrList);
                }
            }
        }

        int cnt=replyDao.countByBoard(boardNo);
        paging.setTotalRows(cnt);
        return replyList;
    }

    @Override
    public int modify(ReplyDto reply) throws Exception {
        return 0;
    }

    @Override
    public int register(ReplyDto reply) throws Exception {
        return 0;
    }

    @Override
    public int remove(int replyNo) throws Exception {
        return 0;
    }
}
