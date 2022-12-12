package com.bd6.board.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BoardDto {
    private int boardNo; //auto increment
    private String title;
    private String contents;
    private Date postTime; //current_timestamp
    private String userId; //user.user_id fk
    private int views; //default=0
    private List<ReplyDto> replyList; //1 : N
    private List<BoardImgDto> boardImgList; //1:N
}
