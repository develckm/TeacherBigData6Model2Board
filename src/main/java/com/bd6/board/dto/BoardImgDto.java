package com.bd6.board.dto;
//table SPRING_BOARD.board_img
public class BoardImgDto {
    private int boardImgNo; //pk auto increment
    private  int boardNo;  //fk board.board_no
    private String imgPath;
}
