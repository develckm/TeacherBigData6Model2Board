package com.bd6.board.dao;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.UserDto;
import org.junit.jupiter.api.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoImpTest {
    static UserDao userDao=null;
    @BeforeEach //모든 Test 마다 처음에 실행
    void setUp() throws Exception {
    }

    @AfterEach //모든 Test 마다 끝에 실행
    void tearDown() throws Exception {
    }
    @BeforeAll //test 시작 전
    static void init() throws Exception {
        userDao=new UserDaoImp();
    }
    @AfterAll //test 완료 후
    static void end() throws  Exception{
        System.out.println("삭제: "+userDao.deleteById("develckm100"));//등록과 동시에 삭제가 되면 실패할 수 있어서...
    }
    @Test
    void findPaging() throws  Exception{
        PagingDto paging=new PagingDto(1,5,"user_id","asc");
        System.out.println(paging);
        System.out.println(userDao.findPaging(paging));
    }

//Test간에 순서가 없기 때문에  userDao 가 객체로 만들어지기 전에 findAll()을 호출하면 오류가 발생
//    @Test
//    void setObj(){
//        try {
//            userDao=new UserDaoImp();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
    @Test
    void findAll() throws Exception {
        System.out.println(userDao.findAll());
    }

    @Test
    void findById() throws Exception {
        System.out.println(userDao.findById("user1"));
    }

//    @Test
//    void deleteById() throws Exception {
//        System.out.println(userDao.deleteById("develckm100"));
//
//    }
    @Test
    void updateById() throws Exception {
        UserDto user=new UserDto();
        user.setUserId("user1");
        user.setName("최경민");
        user.setPw("1234");
        user.setPhone("010-9876-5431");
        user.setEmail("user1@acorn.co.kr");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        user.setBirth(sdf.parse("1986-05-25"));

        System.out.println(userDao.updateById(user));
    }

    @Test
    void insert() throws Exception {
        UserDto user=new UserDto();
        user.setUserId("develckm100");
        user.setName("최경민");
        user.setPw("1234");
        user.setPhone("010-5555-5100");
        user.setEmail("develckm100@gmail.com");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        user.setBirth(sdf.parse("1986-05-25"));
        System.out.println(userDao.insert(user));
        //System.out.println(userDao.findById("develckm"));
    }

    @Test
    void findByUserIdAndPw() throws Exception {
        System.out.println(userDao.findByUserIdAndPw("awriter","1234"));
    }
}