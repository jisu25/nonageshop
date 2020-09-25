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
import nonageshop.dto.Product;
import nonageshop.service.OrderService;

public class MyPageHandler implements Command {

	private OrderService service;
	
	public MyPageHandler() {
		service = new OrderService();
	}
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("loginForm.do");
			return null;
		} else {
			ArrayList<Integer> noList = service.selectOrdering(loginUser.getId());
			ArrayList<OrderDetail> orderList = new ArrayList<OrderDetail>();
			
			for(int no : noList) {
				ArrayList<OrderDetail> orderListing = service.getOrderListById(loginUser.getId(), "1", no);
				
				OrderDetail order = orderListing.get(0);
				Product pdt = order.getProduct();
				pdt.setName(pdt.getName() + " 외 " + orderListing.size() + "건");
				
				int totalPrice = 0;
				for (OrderDetail od : orderListing) {
					totalPrice += od.getProduct().getSalePrice() * od.getQuantity();
				}
				
				pdt.setSalePrice(totalPrice);
				orderList.add(order);
			}
			
			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("orderList", orderList);
		}
		
		return "mypage/mypage.jsp";
	}

}
