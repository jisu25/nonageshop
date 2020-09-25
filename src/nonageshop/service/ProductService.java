package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.ProductDao;
import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Product;

public class ProductService {

	private ProductDao dao = ProductDaoImpl.getInstance();
	
	public ProductService() {
		dao.setConnection(JndiDs.getConnection());
	}
	
	// 신상품 리스트 얻어오기
	public ArrayList<Product> listNewProduct() {
		return dao.listNewProduct();
	}
	
	// 베스트 상품 리스트 얻어오기
	public ArrayList<Product> listBestProduct(){
		return dao.listBestProduct();
	}
	
	// 상품번호로 상품 정보 한 개 얻어오기
	public Product getProduct(int no) {
		return dao.getProduct(no);
	}
	
	// 상품 종류별 리스트
	public ArrayList<Product> listKindProduct(String kind) {
		return dao.listKindProduct(kind);
	}
	
	public int totalRecord(String productName) {
		return dao.totalRecord(productName);
	}
	
	public String pageNumber(int tpage, String name) {
		return dao.pageNumber(tpage, name);
	}
	
	public ArrayList<Product> listProduct(int tpage, String productName) {
		return dao.listProduct(tpage, productName);
	}
	
}
