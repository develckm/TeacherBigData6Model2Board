package com.bd6.board.dao;

import com.bd6.board.dto.BoardDto;

public interface BoardDao extends CRUD<BoardDto,Integer>{
    int updateViews(int boardNo) throws  Exception; //조회수를 1 올리는 업데이트
}
