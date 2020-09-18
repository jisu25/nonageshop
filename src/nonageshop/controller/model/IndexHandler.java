package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class IndexHandler implements Command {

	private ProductService service;
	
	public IndexHandler() {
		service = new ProductService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		ArrayList<Product> newList = service.listNewProduct();
		ArrayList<Product> bestList = service.listBestProduct();
		
		request.setAttribute("newProductList", newList);
		request.setAttribute("bestProductList", bestList);
		
		return "/index.jsp";
	}

}
