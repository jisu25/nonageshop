package nonageshop.controller.model.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class AdminProductListHandler implements Command {

	private ProductService service;
	
	public AdminProductListHandler() {
		service = new ProductService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String key = request.getParameter("key");
		String tpage = request.getParameter("tpage");
		
		if(key == null) {
			key = "";
		}
		
		if(tpage == null || tpage.equals("")) {
			tpage="1";
		} 
		
		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);
		
		ArrayList<Product> productList = service.listProduct(Integer.parseInt(tpage), key);
		String paging = service.pageNumber(Integer.parseInt(tpage), key);
		
		request.setAttribute("productList", productList);
		
		int n = productList.size();
		request.setAttribute("productListSize", n);
		request.setAttribute("paging", paging);
		
		return "admin/product/productList.jsp";
	}

}
