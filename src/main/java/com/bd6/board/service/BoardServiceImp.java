package com.bd6.board.service;

import com.bd6.board.dao.BoardDao;
import com.bd6.board.dao.BoardDaoImp;
import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.PagingDto;

import java.util.List;

public class BoardServiceImp implements BoardService{
    private BoardDao boardDao;

    public BoardServiceImp() throws Exception {
        this.boardDao=new BoardDaoImp();
    }

    @Override
    public int register(BoardDto board) throws Exception {
        return 0;
    }

    @Override
    public int modify(BoardDto board) throws Exception {
        return 0;
    }

    @Override
    public BoardDto detail(int boardNo) throws Exception {
        return null;
    }

    @Override
    public List<BoardDto> list(PagingDto paging) throws Exception {
        List<BoardDto> list=null;
        int totalRows=0;
        list=boardDao.findPaging(paging);
        totalRows=boardDao.count(paging);
        paging.setTotalRows(totalRows);
        return list;
    }
}
