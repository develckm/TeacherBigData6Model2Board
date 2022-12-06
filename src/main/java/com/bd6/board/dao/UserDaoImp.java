package com.bd6.board.dao;

import com.bd6.board.dto.PagingDto;
import com.bd6.board.dto.UserDto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements  UserDao{

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    public UserDaoImp() throws Exception {
        conn=SpringBoardConn.getConn();
    }

    @Override
    public List<UserDto> findAll() throws Exception {
        List<UserDto> userList=null;
        String sql="SELECT * FROM USER";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        userList=new ArrayList<UserDto>();
        while (rs.next()){
            UserDto user=this.rsParsingUser(rs);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<UserDto> findPaging(PagingDto paging) throws Exception {
        List<UserDto> userList=null;
        String sql="SELECT * FROM USER ORDER BY "+paging.getOrderField()+" "+paging.getDirect()+" LIMIT ?,?";
        // "desc or asc 예약어인데 문자열 취급하면  'desc' 로 변형되어서 방법이 없다."
        // mysql 8 버전에서 ORDER BY의 필드명을 문자열 파상할 수 없다. Order by 'name'(x)
        // sql 인젝션 공격을 대비해야한다.!
        System.out.println(sql);
        pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,paging.getStartRow());
        pstmt.setInt(2,paging.getRows());

        rs=pstmt.executeQuery();
        userList=new ArrayList<UserDto>();
        while(rs.next()){
            UserDto user=rsParsingUser(rs);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public int count(PagingDto paging)throws Exception {
        int count=0;
        String sql="SELECT COUNT(*) cnt FROM USER";
        pstmt=conn.prepareStatement(sql);
        rs=pstmt.executeQuery();
        if(rs.next()){
            count=rs.getInt("cnt");
        }
        return count;
    }

    @Override
    public UserDto findById(String id) throws Exception {
        UserDto user=null;
        String sql="SELECT * FROM USER WHERE user_id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        rs=pstmt.executeQuery();
        if(rs.next()){
            user=this.rsParsingUser(rs);
        }
        return user;
    }

    @Override
    public int deleteById(String id) throws Exception {
        int delete=0;
        String sql="DELETE FROM USER WHERE user_id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,id);
        delete=pstmt.executeUpdate();
        return delete;
    }

    @Override
    public int updateById(UserDto dto) throws Exception {
        int update=0;
        String sql="UPDATE USER SET name=?,pw=?,birth=?,email=?,phone=? WHERE user_id=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,dto.getName());
        pstmt.setString(2,dto.getPw());
        pstmt.setDate(3,new Date(dto.getBirth().getTime()));
        pstmt.setString(4,dto.getEmail());
        pstmt.setString(5,dto.getPhone());
        pstmt.setString(6,dto.getUserId());
        update=pstmt.executeUpdate();
        return update;
    }

    @Override
    public int insert(UserDto dto) throws Exception {
        int insert=0;
        String sql="INSERT INTO USER (user_id, name, pw, phone, email, birth)  VALUES (?,?,?,?,?,?)";
        pstmt= conn.prepareStatement(sql);
        pstmt.setString(1,dto.getUserId());
        pstmt.setString(2,dto.getName());
        pstmt.setString(3,dto.getPw());
        pstmt.setString(4,dto.getPhone());
        pstmt.setString(5,dto.getEmail());
        pstmt.setDate(6,new java.sql.Date( dto.getBirth().getTime() ));
        insert=pstmt.executeUpdate();
        return insert;
    }

    @Override
    public UserDto findByUserIdAndPw(String userId, String pw) throws Exception {
        UserDto user=null;
        String sql="SELECT * FROM USER WHERE user_id=? AND pw=?";
        pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,userId);
        pstmt.setString(2,pw);
        rs=pstmt.executeQuery();
        if(rs.next()){
            user=this.rsParsingUser(rs);
        }
        return user;
    }
    public UserDto rsParsingUser(ResultSet rs) throws Exception{
        UserDto user=new UserDto();
        user.setUserId(rs.getString("user_id"));
        user.setName(rs.getString("name"));
        user.setPw(rs.getString("pw"));
        user.setPhone(rs.getString("phone"));
        user.setEmail(rs.getString("email"));
        user.setBirth(rs.getDate("birth"));
        user.setSignup(rs.getDate("signup"));
        return user;
    }

}
