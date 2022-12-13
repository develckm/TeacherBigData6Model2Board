package com.bd6.board.service;

import com.bd6.board.dao.*;
import com.bd6.board.dto.BoardDto;
import com.bd6.board.dto.BoardImgDto;
import com.bd6.board.dto.PagingDto;

import java.io.File;
import java.util.List;

public class BoardServiceImp implements BoardService{
    private BoardDao boardDao;
    private BoardImgDao boardImgDao;
    public BoardServiceImp() throws Exception {
        this.boardDao=new BoardDaoImp();
        this.boardImgDao=new BoardImgDaoImp();
    }

    @Override
    public int register(BoardDto board) throws Exception {
        int register=0;
        register=this.boardDao.insert(board); //pk가 만들어진다.
        //last_insert_id로 방금 저장된 게시글 번호를 불러와서 이미지 저장시 사용해야한다.
        int id= SpringBoardConn.getLastInsertId();
        board.setBoardNo(id); //상세페이지용
        if(board.getBoardImgList()!=null){
            for(BoardImgDto boardImg : board.getBoardImgList()){
                boardImg.setBoardNo(id);
                register+=boardImgDao.insert(boardImg);
            }
        }
        return register;
    }

    @Override
    public int modify(BoardDto board,int [] delBoardImgNos) throws Exception {
        int modify=0;
        modify=boardDao.updateById(board);
        if(board.getBoardImgList()!=null){ //저장된 이미지 경로를 db 저장
            for (BoardImgDto boardImg:board.getBoardImgList()){
                boardImg.setBoardNo(board.getBoardNo());
                modify+=boardImgDao.insert(boardImg);
            }
        }
        int imgDel=0;
        int imgFileDel=0;
        String imgPath=SpringBoardConn.IMG_PATH;
        if(delBoardImgNos!=null){ //삭제할 이미지 넘버들이 오면 db에 삭제
            for (int boardImgNo : delBoardImgNos){
                BoardImgDto boardImg=boardImgDao.findById(boardImgNo);
                File imgFile=new File(imgPath+"/"+boardImg.getImgPath()); //삭제할 이미지 파일 찾기
                imgFileDel+=(imgFile.delete())?1:0;
                imgDel+=boardImgDao.deleteById(boardImgNo);
            }
        }
        System.out.println("이미지 삭제:"+imgDel);
        System.out.println("이미지 파일삭제:"+imgFileDel);
        return modify;
    }

    @Override
    public BoardDto detail(int boardNo) throws Exception {
        BoardDto board= boardDao.findById(boardNo);
        List<BoardImgDto> boardImgList=boardImgDao.findByBoardNo(boardNo); //join 처럼 동작
        board.setBoardImgList(boardImgList);
        return board;
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
