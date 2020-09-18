package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import nonageshop.dao.MemberDao;
import nonageshop.dto.Member;

public class MemberDaoImpl implements MemberDao {

	private static final MemberDaoImpl instance = new MemberDaoImpl();
	private Connection con;
	
	private MemberDaoImpl() {
	}
	
	public static MemberDaoImpl getInstance() {
		return instance;
	}
	
	public void setCon(Connection con) {
		this.con = con;
	}


	@Override
	public int confirmId(String id) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return 1; // 있음
				}
				return 0; // 없음
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public Member getMember(String id) {
		String sql = "SELECT * FROM MEMBER WHERE MEMBER_ID = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getMember(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return null;
	}

	private Member getMember(ResultSet rs) throws SQLException {
		// MEMBER_ID, MEMBER_PWD, MEMBER_NAME, MEMBER_EMAIL, ZIP_NUM, ADDRESS, PHONE, DEL_YN, REG_DATE
		String id = rs.getString("MEMBER_ID");
		String pwd = rs.getString("MEMBER_PWD");
		String name = rs.getString("MEMBER_NAME");
		String email = rs.getString("MEMBER_EMAIL");
		String zipNum = rs.getString("ZIP_NUM");
		String address = rs.getString("ADDRESS");
		String phone = rs.getString("PHONE");
		String delYn = rs.getString("DEL_YN");
		Date regDate = rs.getTimestamp("REG_DATE");
		
		return new Member(id, pwd, name, email, zipNum, address, phone, delYn, regDate);
	}

	@Override
	public int insertMember(Member mem) {
		String sql = "INSERT INTO MEMBER(MEMBER_ID, MEMBER_PWD, MEMBER_NAME, MEMBER_EMAIL, ZIP_NUM, ADDRESS, PHONE) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getPwd());
			pstmt.setString(3, mem.getName());
			pstmt.setString(4, mem.getEmail());
			pstmt.setString(5, mem.getZipNum());
			pstmt.setString(6, mem.getAddress());
			pstmt.setString(7, mem.getPhone());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
