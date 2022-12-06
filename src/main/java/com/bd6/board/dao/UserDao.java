package com.bd6.board.dao;

import com.bd6.board.dto.UserDto;

public interface UserDao extends CRUD<UserDto,String> {
    //Dao의 이름을 정할때 "_" 예약어 필드를 의미 User_id =user.id
    //db의 필드명을 java에 맵핑할 때 꼭 낙타표기법으로 바꿔서 작성해야한다.
    UserDto findByUserIdAndPw(String userId,String pw) throws Exception; //로그인

}
