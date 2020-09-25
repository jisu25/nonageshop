package nonageshop.dao;

import java.sql.Connection;
import java.util.ArrayList;

import nonageshop.dto.Product;

public interface ProductDao {

	void setConnection(Connection con);
	
	// 신상품 리스트 얻어오기
	ArrayList<Product> listNewProduct();
	
	// 베스트 상품 리스트 얻어오기
	ArrayList<Product> listBestProduct();
	
	// 상품번호로 상품 정보 한 개 얻어오기
	Product getProduct(int no);
	
	// 상품 종류별 리스트
	ArrayList<Product> listKindProduct(String kind);
	
	int insertProduct(Product pdt);
	
	int updateProduct(Product pdt);
	
	int deleteProduct(Product pdt);
	
	
	// Admin
	
	int totalRecord(String productName);
	
	String pageNumber(int tpage, String name);
	
	ArrayList<Product> listProduct(int tpage, String productName);
	
	
	
}
