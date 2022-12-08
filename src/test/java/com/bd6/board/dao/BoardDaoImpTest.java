package com.bd6.board.dao;

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
    void deleteById() {
    }

    @Test
    void updateById() {
    }

    @Test
    void insert() {
    }
}