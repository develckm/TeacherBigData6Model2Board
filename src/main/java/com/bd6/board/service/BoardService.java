package com.bd6.board.service;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;

import java.util.List;

public interface BoardService {
    int register(BoardDto board) throws  Exception;
    int modify(BoardDto board) throws  Exception;
    BoardDto detail(int boardNo) throws Exception;
    List<BoardDto> list(PagingDto paging) throws Exception;
}
