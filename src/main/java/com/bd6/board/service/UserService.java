package com.bd6.board.service;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.UserDto;

import java.util.List;

//Data  Access 모델 (Dao) : 쿼리하나가 일어날때마다 발생하는 것을 처리 (보통 CRUD를 정의)
//서비스 모델 (Service) : 유저가 1번의 서비스를 받을 때 일어나는 복수의 쿼리 처리
//예) 웹앱에서 유저가 접속을 할때마다 마지막 접속일을 기록
// -> 로그인 서비스 (select 로 id와 pw가 같은 경우 유저를 반환+유저의 마지막 접속일을 수정)
// 1개의 쿼리만 실행하는 서비스도 습관적으로 서비스를 정의하고 구현한다.
// 트랜잭션 관리가 가능하다. (여러쿼리를 실행하는 하나의 서비스를 커밋과 롤백으로 관리하는 것)
// 서비스는 데이터베이스 접속을 원자화한 dao를 재사용하기 위해 존재하며 서비스 자체가 제사용되기는
// 힘들다.
//service 1개가 1개의 서블릿과 맵핑된다.
public interface UserService {
    //유저에게 제공하는 서비스 (캡슐화)
    //회원가입,회원정보수정,비밀번호 변경  ????
    //관리자가 사용하는 서비스 (오픈)
    //회원정보수정,유저리스트,유저상세,유저전체수정,회원등록
    //공통제공
    //로그인, 아이디 체크, ??????
    int signup(UserDto user) throws Exception;
    int register(UserDto user) throws Exception;

    int userModify(UserDto user) throws Exception;
    int adminModify(UserDto user) throws Exception;

    UserDto login(String id, String pw) throws Exception;
    List<UserDto> list(PagingDto paging) throws Exception; //나중에 페이징으로 바꿀 예정
    UserDto detail(String id) throws Exception;
    int idCheck(String id) throws Exception;//0:존재하지 않는 아이디,1:존재하는 아이디,-1:오류
}
