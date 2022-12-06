package com.bd6.board.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpringBoardDaoTest {

//    @BeforeEach
//    void setUp() {
//    }
//
//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void getConn() {
        try {
            System.out.println(SpringBoardConn.getConn());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}