package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonageshop.controller.Command;
import nonageshop.dto.Kind;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class CategoryHandler implements Command {

	private ProductService service;
	
	public CategoryHandler() {
		 service = new ProductService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("productKind.do >> GET");
			
			String kind = request.getParameter("kind");
			ArrayList<Product> kindList = service.listKindProduct(kind);
			
			kindList.stream().forEach(System.out::println);
			
			request.setAttribute("kindList", kindList);
			
			return "product/productKind.jsp";
		} else {
			System.out.println("productKind.do >> POST");

			List<Kind> kind = Arrays.asList(
					new Kind(1, "Heels"),
					new Kind(2, "Boots"),
					new Kind(3, "Sandals"),
					new Kind(4, "Sneakers"),
					new Kind(5, "On sales")
					);
			
			Gson gson = new Gson();
			String kindJson = gson.toJson(kind, new TypeToken<List<Kind>>(){}.getType());
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("Application/json");
			response.getWriter().print(kindJson);
			
			return null;
		}
	}

}
