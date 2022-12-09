package com.bd6.board.dao;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.ReplyDto;

import java.util.List;

public interface ReplyDao extends CRUD<ReplyDto,Integer>{
    List<ReplyDto> findByBoardNo(PagingDto paging, int boardNo) throws  Exception;
    int countByBoard(int boardNo) throws Exception;
}
