package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.service.OrderService;

public class OrderListHandler implements Command {

	private OrderService service;
	
	public OrderListHandler() {
		service = new OrderService();
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String url = "mypage/orderList.jsp";
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			url = "loginForm.do";
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			ArrayList<OrderDetail> orderList = service.getOrderListById(loginUser.getId(), "1", no);
			
			int totalPrice = 0;
			for(OrderDetail order : orderList) {
				totalPrice += order.getProduct().getSalePrice() * order.getQuantity();
			}
			
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
		}
		
		return url;
	}

}
