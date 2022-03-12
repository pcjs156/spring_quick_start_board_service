package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

// DAO (Data Access Object)
@Repository("boardDAO")
public class BoardDAO {
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

    // CRUD 기능의 메소드 구현
    // 글 등록
    public void insertBoard(BoardVO vo) {
        System.out.println("===> JDBC로 insertBoard() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_INSERT);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getWriter());
            stmt.setString(3, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 수정
    public void updateBoard(BoardVO vo) {
        System.out.println("===> JDBC로 updateBoard() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_UPDATE);
            stmt.setString(1, vo.getTitle());
            stmt.setString(2, vo.getContent());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 삭제
    public void deleteBoard(BoardVO vo) {
        System.out.println("===> JDBC로 deleteBoard() 기능 처리");

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_DELETE);
            stmt.setInt(1, vo.getSeq());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }
    }

    // 글 상세 조회
    public BoardVO getBoard(BoardVO vo) {
        System.out.println("===> JDBC로 getBoard() 기능 처리");
        BoardVO board = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_GET);
            stmt.setInt(1, vo.getSeq());
            rs = stmt.executeQuery();

            if (rs.next()) {
                board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regdate"));
                board.setCnt(rs.getInt("CNT"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return board;
    }

    // 글 목록 조회
    public List<BoardVO> getBoardList(BoardVO vo) {
        System.out.println("===> JDBC로 getBoardList() 기능 처리");
        List<BoardVO> boardList = new ArrayList<>();

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement(BOARD_LIST);
            rs = stmt.executeQuery();

            while (rs.next()) {
                BoardVO board = new BoardVO();
                board.setSeq(rs.getInt("seq"));
                board.setTitle(rs.getString("title"));
                board.setWriter(rs.getString("writer"));
                board.setContent(rs.getString("content"));
                board.setRegDate(rs.getDate("regdate"));
                board.setCnt(rs.getInt("CNT"));
                boardList.add(board);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(stmt, conn);
        }

        return boardList;
    }
}
