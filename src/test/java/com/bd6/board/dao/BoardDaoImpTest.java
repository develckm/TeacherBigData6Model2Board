package com.bd6.board.dao;

import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardDaoImpTest {
    static BoardDao boardDao;
    @BeforeAll
    static void init() throws Exception {
        boardDao=new BoardDaoImp();
    }
    @Test
    void findAll() throws Exception {
        System.out.println(boardDao.findAll());
    }

    @Test
    void findPaging() throws Exception{
        PagingDto paging=new PagingDto(2,3,"title","ASC");
        System.out.println(boardDao.findPaging(paging));
    }

    @Test
    void count() throws Exception {
        System.out.println(boardDao.count(null));
    }

    @Test
    void findById() throws Exception{
        System.out.println(boardDao.findById(1));
    }

    @Test
    void deleteById() throws Exception {
        System.out.println(boardDao.findById(5));
        System.out.println(boardDao.deleteById(5));
        System.out.println(boardDao.findById(5));
    }

    @Test
    void updateById() throws Exception {
        BoardDto board=new BoardDto();
        board.setBoardNo(1);
        board.setTitle("수정 테스트 단위 테스트 중입니다!!");
        board.setContents("내용도 수정 테스트 중입니다.");
        System.out.println(boardDao.updateById(board));
        System.out.println(boardDao.findById(1));
    }
    @Test
    void updateViews()throws Exception{
        System.out.println(boardDao.updateViews(1));
        System.out.println(boardDao.findById(1));
    }


    @Test
    void insert() throws Exception{
        BoardDto board=new BoardDto();
        board.setTitle("안녕하세요! 테스트 보드입니다.");
        board.setContents("삭제될 테스트 입니다.");
        board.setUserId("user1");
        System.out.println(boardDao.insert(board));
    }
}