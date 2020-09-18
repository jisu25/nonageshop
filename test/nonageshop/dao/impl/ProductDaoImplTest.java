package nonageshop.dao.impl;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import nonageshop.dao.ProductDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Product;

public class ProductDaoImplTest {

	private static ProductDao dao = ProductDaoImpl.getInstance();
	private static Connection con = JdbcUtil.getConnection();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setConnection(con);
	}

	@Test
	public void testListNewProduct() {
		System.out.println("testListNewProduct()");
		ArrayList<Product> list = dao.listNewProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testListBestProduct() {
		System.out.println("testListBestProduct()");
		ArrayList<Product> list = dao.listBestProduct();
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}

	@Test
	public void testGetProduct() {
		System.out.println("testListBestProduct()");
		Product pdt = dao.getProduct(1);
		Assert.assertNotNull(pdt);
		System.out.println(pdt);
	}

	@Test
	public void testListKindProduct() {
		System.out.println("testListKindProduct()");
		ArrayList<Product> list = dao.listKindProduct("1");
		Assert.assertNotNull(list);
		list.stream().forEach(System.out::println);
	}
}
