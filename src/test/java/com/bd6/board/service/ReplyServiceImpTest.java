package com.bd6.board.service;

import com.bd6.board.dto.PagingDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReplyServiceImpTest {
    static ReplyService replyService;
    @BeforeAll
    static void init() throws Exception {
        replyService=new ReplyServiceImp();
    }

    @Test
    void list() {
    }

    @Test
    void boardDetailList() throws Exception {
        PagingDto paging=new PagingDto(1,3,"reply_no","ASC");
        System.out.println(paging);
        System.out.println(replyService.boardDetailList(paging,1));
        System.out.println(paging);
    }

    @Test
    void modify() {
    }

    @Test
    void register() {
    }

    @Test
    void remove() {
    }
}