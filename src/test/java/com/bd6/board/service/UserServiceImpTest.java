package com.bd6.board.service;

import com.bd6.board.dto.PagingDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImpTest {
    private static UserService userService;
    @BeforeAll
    static void init() throws Exception {
        userService=new UserServiceImp();
    }

    @Test
    void signup() {
    }

    @Test
    void register() {
    }

    @Test
    void userModify() {
    }

    @Test
    void adminModify() {
    }

    @Test
    void login() {
    }

    @Test
    void list() throws Exception {
        PagingDto paging=new PagingDto(1,10,"name","asc");
        System.out.println(userService.list(paging));
    }

    @Test
    void detail() {
    }

    @Test
    void idCheck() {
    }
}