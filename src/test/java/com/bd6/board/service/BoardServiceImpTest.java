package com.bd6.board.service;

import com.bd6.board.dto.PagingDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceImpTest {
    static BoardService boardService;
    @BeforeAll
    static void init() throws Exception {
        boardService=new BoardServiceImp();
    }
    @Test
    void register() {
    }

    @Test
    void modify() {
    }

    @Test
    void detail() {
    }

    @Test
    void list() throws Exception{
        PagingDto paging=new PagingDto(7,3,"title","ASC");
        System.out.println(boardService.list(paging));
        System.out.println(paging);
    }
}