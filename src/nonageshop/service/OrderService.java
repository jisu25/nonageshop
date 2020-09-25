package nonageshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.OrderDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dao.impl.OrderDaoImpl;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Cart;
import nonageshop.dto.OrderDetail;

public class OrderService {

	private OrderDao orderDao = OrderDaoImpl.getInstance();
	private CartDao cartDao = CartDaoImpl.getInstance();
	
	
	public OrderService() {
		orderDao.setCon(JndiDs.getConnection());
	}
	
	public int insertOrder(ArrayList<Cart> cartList, String id) {
		return orderDao.insertOrder(cartList, id);
	}
	
	public ArrayList<OrderDetail> getOrderListById(String id, String result, int no) {
		return orderDao.listOrderById(id, result, no);
	}
	
	public ArrayList<Integer> selectOrdering(String id){
		return orderDao.selectOrdering(id);
	}
	
	public int addOrderAndDetail(ArrayList<Cart> cartList, String id) {
		String ordersSql = "INSERT INTO ORDERS(MEMBER_ID) VALUES(?)";
		String detailSql = "INSERT INTO ORDER_DETAIL(ORDERS_NO, PRODUCT_NO, ORDER_QUANTITY) VALUES((SELECT MAX(ORDERS_NO) FROM ORDERS), ?, ?)";
		
		Connection con = null;
		PreparedStatement orderPstmt = null;
		PreparedStatement detailPstmt = null;
		
		int maxNo = 0;
		
		try {
			con = JndiDs.getConnection();
			con.setAutoCommit(false);
			
			/* orders에 insert 하기 */
			orderPstmt = con.prepareStatement(ordersSql);
			orderPstmt.setString(1, id);
			orderPstmt.executeUpdate();
			
			
			/* details에 insert 하기 */
			detailPstmt = con.prepareStatement(detailSql);
			maxNo = orderDao.selectMaxOrdersNo();

			for(Cart cart : cartList) {
				detailPstmt.setInt(1, cart.getProduct().getNo());
				detailPstmt.setInt(2, cart.getQuantity());
				detailPstmt.executeUpdate();
				
				cartDao.updateCartResult(cart);
			}
			
			con.commit();
		} catch (SQLException e) {
			rollbackUtil(con, e);
		} finally {
			closeUtil(con, orderPstmt, detailPstmt);
		}
		
		return maxNo;
	}
	
	
	private void rollbackUtil(Connection con, SQLException e) {
        try {
            System.out.println("roll back");
            con.rollback();
            throw new RuntimeException(e);
        } catch (SQLException ex) {
        }
    }

    private void closeUtil(Connection con, PreparedStatement dPstmt, PreparedStatement tPstmt) {
        try {
            if (dPstmt != null) {
                dPstmt.close();
            }
            if (tPstmt != null) {
                tPstmt.close();
            }
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
        }
    }
}
