package kr.or.kosa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import kr.or.kosa.dto.Board;

public class BoardDao {
	
	DataSource ds = null;
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	//전체보기
	public List<Board> list(int cpage, int pagesize){
		
		List<Board> list = null;
		ResultSet rs = null;
		try {
			
			conn = ds.getConnection();
			String sql = "select * from " +
                    "(select rownum rn,idx,writer,email,pwd,subject , content, writedate, readnum " +
                    ",filename,filesize,refer,depth,step " +
                    " from ( SELECT * FROM jspboard ORDER BY refer DESC , step ASC ) "+
                    " where rownum <= ?" +  //endrow
                    ") where rn >= ?"; //startrow
			pstmt = conn.prepareStatement(sql);
			//공식같은 로직
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			//
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<Board>();
			while(rs.next()) {
				Board board = new Board();
				board.setIdx(rs.getInt("idx"));
				board.setSubject(rs.getString("subject"));
				board.setWriter(rs.getString("writer"));
				board.setWritedate(rs.getDate("writedate"));
				board.setReadnum(rs.getInt("readnum"));
				
				//계층형
				board.setRefer(rs.getInt("refer"));
				board.setStep(rs.getInt("step"));
				board.setDepth(rs.getInt("depth"));
				
				list.add(board);
			}
			
		} catch (Exception e) {
			System.out.println("List function error : " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
		}
			
		return list;
	}
	
	//게시물 총 건수 구하기
	public int totalBoardCount() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalcount = 0;
			try {
				conn = ds.getConnection(); //dbcp 연결객체 얻기
				String sql="select count(*) cnt from jspboard";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					totalcount = rs.getInt("cnt");
				}
			}catch (Exception e) {
				
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환  connection pool 에 반환하기
				}catch (Exception e) {
					
				}
			}
			return totalcount;
		}
	
	
	//게시글 작성
	public int writeok(Board boarddata) {
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return row;
	}
	
	//게시글 상세보기
	
	//게시글 조회수 증가
	
	//게시글 삭제하기
	
	//댓글 입력하기(Table reply : fk(jspboard idx))
	
	//댓글 조회하기
	
	//댓글 삭제하기
	
	//게시글 상세(답글 쓰기)
	
	//게시글 수정
	
	
}
