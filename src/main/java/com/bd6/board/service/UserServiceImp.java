package com.bd6.board.service;

import com.bd6.board.dao.UserDao;
import com.bd6.board.dao.UserDaoImp;
import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.UserDto;

import java.util.List;

public class UserServiceImp implements  UserService{
    private UserDao userDao;
    public UserServiceImp() throws Exception {
        this.userDao=new UserDaoImp();
    }

    @Override
    public int signup(UserDto user) throws Exception {
        return 0;
    }

    @Override
    public int register(UserDto user) throws Exception {
        return 0;
    }

    @Override
    public int userModify(UserDto user) throws Exception {
        return 0;
    }

    @Override
    public int adminModify(UserDto user) throws Exception {
        return 0;
    }

    @Override
    public UserDto login(String id, String pw) throws Exception {
        return null;
    }

    @Override
    public List<UserDto> list(PagingDto paging) throws Exception {
        List<UserDto> list=null;
        list=userDao.findPaging(paging);
        int count=userDao.count(paging);
        paging.setTotalRows(count);

        return list;
    }

    @Override
    public UserDto detail(String id) throws Exception {
        return null;
    }

    @Override
    public int idCheck(String id) throws Exception {
        int idCheck=0;
        UserDto user=userDao.findById(id);
        System.out.println(user);
        if(user!=null){
            idCheck=1;
        }
        return idCheck;
    }
}
