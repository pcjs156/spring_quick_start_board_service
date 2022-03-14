package com.springbook.biz.board.impl;

import com.springbook.biz.board.BoardVO;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardRowMapper implements RowMapper<BoardVO> {
    @Override
    public BoardVO mapRow(ResultSet rs, int i) throws SQLException {
        BoardVO board = new BoardVO();

        board.setSeq(rs.getInt("seq"));
        board.setTitle(rs.getString("title"));
        board.setWriter(rs.getString("writer"));
        board.setContent(rs.getString("content"));
        board.setRegDate(rs.getDate("regdate"));
        board.setCnt(rs.getInt("cnt"));

        return board;
    }
}
