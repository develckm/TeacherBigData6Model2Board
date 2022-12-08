package com.bd6.board.dto;

import lombok.Data;

import java.util.Date;
@Data
public class BoardDto {
    private int boardNo;
    private String title;
    private String contents;
    private Date postTime;
    private String userId;
    private int views;
}
