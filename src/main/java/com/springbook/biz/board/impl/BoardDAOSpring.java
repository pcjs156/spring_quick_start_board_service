package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository("boardDAOSpring")
public class BoardDAOSpring extends JdbcDaoSupport {
    private static final String TABLE_NAME = "board";

    // JDBC 관련 변수
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    // SQL 명령어들
    private final String BOARD_INSERT = "INSERT INTO " + TABLE_NAME + "(seq, title, writer, content) " +
            "VALUES((SELECT nvl(max(seq), 0)+1 FROM " + TABLE_NAME + "), ?, ?, ?)";
    private final String BOARD_UPDATE = "UPDATE " + TABLE_NAME + " SET title=?, content=? WHERE seq=?";
    private final String BOARD_DELETE = "DELETE FROM " + TABLE_NAME + " WHERE seq=?";
    private final String BOARD_GET = "SELECT * FROM " + TABLE_NAME + " WHERE seq=?";
    private final String BOARD_LIST = "SELECT * FROM " + TABLE_NAME + " ORDER BY seq DESC";

    @Autowired
    public void setSuperDataSource(DataSource dataSource) {
        super.setDataSource(dataSource);
    }

    // CRUD 기능의 메소드 구현
    // 글 등록
    public void insertBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 insertBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_INSERT, vo.getTitle(), vo.getWriter(), vo.getContent());
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 updateBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_UPDATE, vo.getTitle(), vo.getContent(), vo.getSeq());
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 deleteBoard() 기능 처리");
        getJdbcTemplate().update(BOARD_DELETE, vo.getSeq());
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> Spring JDBC로 getBoard() 기능 처리");
        Object[] args = {vo.getSeq()};
        return getJdbcTemplate().queryForObject(BOARD_GET, args, new BoardRowMapper());
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("===> Spring JDBC로 getBoardList() 기능 처리");
        return getJdbcTemplate().query(BOARD_LIST, new BoardRowMapper());
    }

}
