package nonageshop.service;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Cart;

public class CartService {

	private CartDao dao = CartDaoImpl.getInstance();
	
	public CartService() {
		dao.setCon(JndiDs.getConnection());
	}
	
	public int insertCart(Cart cart) {
		return dao.insertCart(cart);
	}

	public int deleteCart(Cart cart) {
		return dao.deleteCart(cart);
	}
	
	public ArrayList<Cart> listCart(String id) {
		return dao.listCart(id);
	}
}


