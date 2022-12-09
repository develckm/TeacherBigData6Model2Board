package com.bd6.board.dao;

import com.bd6.board.dto.PagingDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyDaoImpTest {
    static ReplyDao replyDao;
    @BeforeAll
    static void init() throws Exception {
        replyDao=new ReplyDaoImp();
    }
    @Test
    void findAll() {
    }

    @Test
    void findPaging() {
    }

    @Test
    void count() {
    }

    @Test
    void findById() {
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

    @Test
    void findByBoardNo() throws Exception {
        PagingDto paging=new PagingDto(1,5,"reply_no","DESC");
        System.out.println(replyDao.findByBoardNo(paging,1));
    }

    @Test
    void rsParseReplyDto() {
    }

    @Test
    void countByBoard() throws Exception {
        System.out.println(replyDao.countByBoard(1));
    }
}