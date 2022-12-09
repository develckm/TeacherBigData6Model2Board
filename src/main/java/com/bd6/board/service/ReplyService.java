package com.bd6.board.service;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;

import java.util.List;

public interface ReplyService {
    List<ReplyDto> list(PagingDto paging) throws Exception; //관리 댓글 관리
    List<ReplyDto> boardDetailList(PagingDto paging,int boardNo) throws Exception; //게시글의 댓글
    int modify(ReplyDto reply) throws Exception;
    int register(ReplyDto reply) throws Exception;
    int remove(int replyNo) throws Exception;
}
