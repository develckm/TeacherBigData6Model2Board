package com.bd6.board.dao;

import com.bd6.board.dto.BoardImgDto;

import java.util.List;

public interface BoardImgDao {
    //
    List<BoardImgDto> findByBoardNo(int boardNo) throws  Exception; //게시글 상세에서 이미지 리스트 출력
    int deleteByBoard(int boardNo) throws  Exception; //게시글이 참조하고 있는 자식(이미지)이 있는 경우 삭제
    int deleteById(int boardImgNo) throws  Exception; //게시글 수정될 때 삭제
    int insert(BoardImgDto boardImg) throws  Exception; //게시글 수정 및 등록 때 등록

}
