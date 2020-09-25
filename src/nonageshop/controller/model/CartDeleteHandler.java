package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.service.CartService;

public class CartDeleteHandler implements Command {
	
	private CartService service;
	
	public CartDeleteHandler() {
		service = new CartService();
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String[] noArr = request.getParameterValues("no");
		
		for(String no : noArr) {
			service.deleteCart(new Cart(Integer.parseInt(no)));
		}
				
		return "cartList.do";
	}

}
