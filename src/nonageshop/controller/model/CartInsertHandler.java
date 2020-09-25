package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;
import nonageshop.service.CartService;

public class CartInsertHandler implements Command {

	private CartService service;
	
	public CartInsertHandler() {
		service = new CartService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String url = "cartList.do";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "loginForm.do";
		} else {
			Cart cart = new Cart();
			
			Member member = new Member(loginUser.getId());
			Product product = new Product(Integer.parseInt(request.getParameter("no")));
			
			cart.setMember(member);
			cart.setProduct(product);
			cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
			
			int res = service.insertCart(cart);
			System.out.println("res : " + res + ", cart : " + cart);
		}
		
		response.sendRedirect(url);
		
		return null;
	}

}
