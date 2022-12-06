package com.bd6.board.dto;

import lombok.Data;

import java.util.Date;
@Data
//SPRING_BOARD.User
public class UserDto {

    private String userId; //pk user_id
    private String name;
    private String pw;
    private String phone; //uk
    private String email; //uk
    private Date birth;
    private Date signup; //default CURRENT_TIME_STAMP()
}
