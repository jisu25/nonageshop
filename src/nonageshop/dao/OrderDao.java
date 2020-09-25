package nonageshop.dao;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dto.Cart;
import nonageshop.dto.OrderDetail;

public interface OrderDao {

	void setCon(Connection con);
	
	int insertOrder(ArrayList<Cart> cartList, String id);
	
	int insertOrderDetail(Cart cart);
	
	ArrayList<OrderDetail> listOrderById(String id, String result, int no);
	
	ArrayList<Integer> selectOrdering(String id);
	
	int selectMaxOrdersNo();
}
