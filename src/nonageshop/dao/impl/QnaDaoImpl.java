package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.QnaDao;
import nonageshop.dto.Member;
import nonageshop.dto.Qna;

public class QnaDaoImpl implements QnaDao {

	private static final QnaDaoImpl instance = new QnaDaoImpl();
	private Connection con;
	private QnaDaoImpl() {}
	
	public static QnaDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Qna> selectQnaById(String id) {
		String sql = "SELECT * FROM QNA WHERE MEMBER_ID = ? ORDER BY QNA_NO DESC";
		ArrayList<Qna> list = new ArrayList<Qna>();
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()) {
					list.add(getQna(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return list;
	}

	private Qna getQna(ResultSet rs) throws SQLException {
		int no = rs.getInt("QNA_NO");
		String subject = rs.getString("SUBJECT");
		String content = rs.getString("QNA_CONTENT");
		String reply = rs.getString("REPLY");
		Member member = new Member(rs.getString("MEMBER_ID"));
		String resYn = rs.getString("RES_YN");
		Date regDate = rs.getTimestamp("REG_DATE");
		
		return new Qna(no, subject, content, reply, member, resYn, regDate);
	}

	@Override
	public Qna selectQnaByNo(int no) {
		String sql = "SELECT * FROM QNA WHERE QNA_NO = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getQna(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}

	@Override
	public int insertQna(Qna qna, String sessionId) {
		String sql = "INSERT INTO QNA(SUBJECT, QNA_CONTENT, MEMBER_ID) VALUES(?, ?, ?)";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, qna.getSubject());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, sessionId);
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
