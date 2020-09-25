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

public class CartListHandler implements Command {

	private CartService service;
	
	public CartListHandler() {
		service = new CartService();
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String url = "mypage/cartList.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "loginForm.do";
			response.sendRedirect(url);
			return null;
		} else {
			System.out.println("loginUser : " + loginUser.getId());
			ArrayList<Cart> cartList = service.listCart(loginUser.getId());
			
			int totalPrice = 0;
			for (Cart cart : cartList) {
				totalPrice += cart.getProduct().getSalePrice() * cart.getQuantity();
			}
			
			request.setAttribute("cartList", cartList);
			request.setAttribute("totalPrice", totalPrice);
			
			return url;
		}
	}
	
	
	
}
