package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class ProductDetailHandler implements Command {

	private ProductService service;
	
	public ProductDetailHandler() {
		service = new ProductService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("productDetail.do >> GET");
			
			int no = Integer.parseInt(request.getParameter("no"));
			Product product = service.getProduct(no);
			
			request.setAttribute("product", product);
			
		} else {
			System.out.println("productDetail.do >> POST");
			
		}
		return "/product/productDetail.jsp";
	}

}
