package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.service.CartService;
import nonageshop.service.OrderService;

public class OrderInsertHandler implements Command {

	private CartService cService;
	private OrderService oService;
	
	public OrderInsertHandler() {
		cService = new CartService();
		oService = new OrderService();
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String url = "orderList.do";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "loginForm.do";
		} else {
			ArrayList<Cart> cartList = cService.listCart(loginUser.getId());
			int no = oService.addOrderAndDetail(cartList, loginUser.getId());
			url = "orderList.do?no=" + no; 
		}
		
		response.sendRedirect(url);
		
		return null;
	}

}
