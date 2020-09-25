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

public class OrderDetailHandler implements Command {

	private OrderService service;
	
	public OrderDetailHandler() {
		service = new OrderService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		System.out.println("OrderDetailHandler >> ");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("loginForm.do");
			return null;
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			ArrayList<OrderDetail> orderList = service.getOrderListById(loginUser.getId(), "1", no);
			
			int totalPrice = 0;
			
			for(OrderDetail od : orderList) {
				totalPrice += od.getProduct().getSalePrice() * od.getQuantity();
			}
			
			request.setAttribute("orderDetail", orderList.get(0));
			request.setAttribute("orderList", orderList);
			request.setAttribute("totalPrice", totalPrice);
			
			return "mypage/orderDetail.jsp";
		}
		
	}

}
