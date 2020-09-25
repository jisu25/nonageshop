package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.CartDao;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;

public class CartDaoImpl implements CartDao {

	private static final CartDaoImpl instance = new CartDaoImpl();
	private Connection con;
	
	private CartDaoImpl() {};
	
	public static CartDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	@Override
	public int insertCart(Cart cart) {
		String sql = "INSERT INTO CART(MEMBER_ID, PRODUCT_NO, QUANTITY) VALUES(?, ?, ?)";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, cart.getMember().getId());
			pstmt.setInt(2, cart.getProduct().getNo());
			pstmt.setInt(3, cart.getQuantity());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public int deleteCart(Cart cart) {
		String sql = "DELETE CART WHERE CART_NO = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, cart.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	@Override
	public ArrayList<Cart> listCart(String id) {
		String sql = "SELECT * FROM CART WHERE MEMBER_ID = ? AND RESULT = 1 ORDER BY CART_NO";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				ArrayList<Cart> list = new ArrayList<>();
				while(rs.next()) {
					list.add(getCart(rs));
				}
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	private Cart getCart(ResultSet rs) throws SQLException {
		// CART_NO, MEMBER_ID, PRODUCT_NO, QUANTITY, REG_DATE
		
		int no = rs.getInt("CART_NO");
		Member member = MemberDaoImpl.getInstance().getMember(rs.getString("MEMBER_ID"));
		Product product = ProductDaoImpl.getInstance().getProduct(rs.getInt("PRODUCT_NO"));
		int quantity = rs.getInt("QUANTITY");
		Date regDate = rs.getTimestamp("REG_DATE");
		
		return new Cart(no, member, product, quantity, regDate);
	}

	
	public int updateCartResult(Cart cart) {
		String sql = "UPDATE CART SET RESULT = 2 WHERE CART_NO = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, cart.getNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
