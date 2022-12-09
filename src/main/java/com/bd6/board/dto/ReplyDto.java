package com.bd6.board.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReplyDto {
    private int replyNo;
    private String title;
    private String contents;
    private Date postTime;
    private String imgPath;
    private int boardNo;
    private String userId;
    private Integer fkReplyNo;
    private List<ReplyDto> replyList; // 1 : N (fkReplyNo)
}
