package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.OrderDao;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Order;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Product;

public class OrderDaoImpl implements OrderDao {

	private static final OrderDaoImpl instance = new OrderDaoImpl();
	private Connection con;
	
	private OrderDaoImpl() {
		con = JndiDs.getConnection();
	}

	public static OrderDaoImpl getInstance() {
		return instance;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
	@Override
	public int insertOrder(ArrayList<Cart> cartList, String id) {
		String sql = "INSERT INTO ORDERS(MEMBER_ID) VALUES(?)";
		
		int maxNo = 0;
		PreparedStatement pstmt = null;	
		ResultSet rs = null;
		
		try {
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			pstmt.close();
			
			for(Cart cart : cartList) {
				insertOrderDetail(cart);
			}
			
			sql = "SELECT MAX(ORDERS_NO) AS MAX_NO FROM ORDERS";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				maxNo = rs.getInt("MAX_NO");
			}
			
		} catch(SQLException | RuntimeException e) {
			e.printStackTrace();
		} finally {
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return maxNo;
	}

	@Override
	public int insertOrderDetail(Cart cart) {
		String sql = "INSERT INTO ORDER_DETAIL(ORDERS_NO, PRODUCT_NO, ORDER_QUANTITY) VALUES((SELECT MAX(ORDERS_NO) FROM ORDERS), ?, ?)";
		
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cart.getProduct().getNo());
			pstmt.setInt(2, cart.getQuantity());
			return pstmt.executeUpdate();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	@Override
	public ArrayList<OrderDetail> listOrderById(String id, String result, int no) {
		String sql = "SELECT * FROM ORDER_VIEW WHERE MEMBER_ID = ? AND RESULT LIKE '%1%' AND ORDERS_NO = ? ORDER BY ORDER_DETAIL_NO";
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
//			pstmt.setString(2, result);
			pstmt.setInt(2, no);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					ArrayList<OrderDetail> list = new ArrayList<>();
					do {
						list.add(getOrderDetail(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			
		}
		return null;
	}

	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
		// ORDER_DETAIL_NO, o.ORDERS_NO, o.MEMBER_ID, ORDERS_DATE, d.PRODUCT_NO, ORDER_QUANTITY, MEMBER_NAME,
	    // ZIP_NUM, ADDRESS, PHONE, PRODUCT_NAME, SALE, RES_YN result
		int no = rs.getInt("ORDER_DETAIL_NO");
		
		Member member = new Member();
		member.setId(rs.getString("MEMBER_ID"));
		member.setName(rs.getString("MEMBER_NAME"));
		member.setZipNum(rs.getString("ZIP_NUM"));
		member.setAddress(rs.getString("ADDRESS"));
		member.setPhone(rs.getString("PHONE"));
		
		Order order = new Order();
		order.setNo(rs.getInt("ORDERS_NO"));
		order.setMember(member);
		order.setOrderDate(rs.getTimestamp("ORDERS_DATE"));
		
		Product product = new Product();
		product.setNo(rs.getInt("PRODUCT_NO"));
		product.setName(rs.getString("PRODUCT_NAME"));
		product.setSalePrice(rs.getInt("SALE"));
		
		int quantity = rs.getInt("ORDER_QUANTITY");
		String resYn = rs.getString("RESULT");
		
		OrderDetail newOrder = new OrderDetail(no, order, product, quantity, resYn);
		System.out.println(newOrder);
		return newOrder;
	}

	@Override
	public ArrayList<Integer> selectOrdering(String id) {
		String sql = "SELECT DISTINCT ORDERS_NO FROM ORDER_VIEW " +
				"WHERE MEMBER_ID = ? AND RESULT = '1' ORDER BY ORDERS_NO";
		
		ArrayList<Integer> noList = new ArrayList<Integer>();
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.executeQuery();
			
			try(ResultSet rs = pstmt.executeQuery()) {
				while(rs.next()){
					noList.add(rs.getInt("ORDERS_NO"));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
		
		return noList;
	}
	
	public int selectMaxOrdersNo() {
		String sql = "SELECT MAX(ORDERS_NO) AS MAX_NO FROM ORDERS";
		int maxNo = 0;
		
		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				maxNo = rs.getInt("MAX_NO");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return maxNo;
	}

}
