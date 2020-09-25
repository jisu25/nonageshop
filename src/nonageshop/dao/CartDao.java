package nonageshop.dao;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dto.Cart;

public interface CartDao {

	void setCon(Connection con);
	
	int insertCart(Cart cart);
	
	int deleteCart(Cart cart);
	
	ArrayList<Cart> listCart(String id);
	
	int updateCartResult(Cart cart);
	
}
