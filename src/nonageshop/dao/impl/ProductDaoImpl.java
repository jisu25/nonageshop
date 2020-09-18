package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.ProductDao;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Product;

public class ProductDaoImpl implements ProductDao {

	private Connection con = JndiDs.getConnection();
	
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	
	private ProductDaoImpl() {};
	
	public static ProductDaoImpl getInstance() {
		return instance;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	@Override
	public ArrayList<Product> listNewProduct() {
		String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, SALE, PRODUCT_IMAGE FROM new_pro_view";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery()) {
			
			ArrayList<Product> list = new ArrayList<Product>();
			if(rs.next()) {
				do {
					list.add(getBestProduct(rs));
				} while(rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	
	private Product getBestProduct(ResultSet rs) throws SQLException {
		int no = rs.getInt("PRODUCT_NO");
		String name = rs.getString("PRODUCT_NAME");
		int salePrice = rs.getInt("SALE");
		String image = rs.getString("PRODUCT_IMAGE");
		
		return new Product(no, name, salePrice, image);
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		int no = rs.getInt("PRODUCT_NO");
		String name = rs.getString("PRODUCT_NAME");
		String kind = rs.getString("PRODUCT_KIND");
		int price = rs.getInt("PRICE");
		int salePrice = rs.getInt("SALE");
		int margin = rs.getInt("MARGIN");
		String content = rs.getString("PRODUCT_CONTENT");
		String image = rs.getString("PRODUCT_IMAGE");
		String delYn = rs.getString("DEL_YN");
		String bestYn = rs.getString("BEST_YN");
		Date regDate = rs.getTimestamp("REG_DATE");
		
		return new Product(no, name, kind, price, salePrice, margin, content, image, delYn, bestYn, regDate);
	}

	@Override
	public ArrayList<Product> listBestProduct() {
		String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, "
				+ "DEL_YN, BEST_YN, REG_DATE FROM product WHERE BEST_YN = 'y'";

		try(PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
				
				ArrayList<Product> list = new ArrayList<Product>();
				if(rs.next()) {
					do {
						list.add(getProduct(rs));
					} while(rs.next());
					return list;
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			return null;
	}

	@Override
	public Product getProduct(int no) {
		String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, "
				+ "DEL_YN, BEST_YN, REG_DATE FROM product WHERE PRODUCT_NO = ?";

		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	
	@Override
	public ArrayList<Product> listKindProduct(String kind) {
		String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE, "
				+ "DEL_YN, BEST_YN, REG_DATE FROM product WHERE PRODUCT_KIND = ?";
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, kind);
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					ArrayList<Product> list = new ArrayList<Product>();
					do {
						list.add(getProduct(rs));
					} while(rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public int insertProduct(Product pdt) {
		String sql = "insert into product(PRODUCT_NO, PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, MARGIN, PRODUCT_CONTENT, PRODUCT_IMAGE)" + 
				"values(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			if(pdt.getNo() == 0) {
				pstmt.setString(1, null);
			} else {
				pstmt.setInt(1, pdt.getNo());
			}
			pstmt.setString(2, pdt.getName());
			pstmt.setString(3, pdt.getKind());
			pstmt.setInt(4, pdt.getPrice());
			pstmt.setInt(5, pdt.getSalePrice());
			pstmt.setInt(6, pdt.getMargin());
			pstmt.setString(7, pdt.getContent());
			pstmt.setString(8, pdt.getImage());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int updateProduct(Product pdt) {
		String sql = "UPDATE PRODUCT SET PRODUCT_NAME = ?, PRODUCT_KIND = ?, PRICE = ?, SALE = ?, MARGIN = ?, PRODUCT_CONTENT = ?, PRODUCT_IMAGE = ? "
				+ "WHERE PRODUCT_NO = ?";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setString(1, pdt.getName());
			pstmt.setString(2, pdt.getKind());
			pstmt.setInt(3, pdt.getPrice());
			pstmt.setInt(4, pdt.getSalePrice());
			pstmt.setInt(5, pdt.getMargin());
			pstmt.setString(6, pdt.getContent());
			pstmt.setString(7, pdt.getImage());
			pstmt.setInt(8, pdt.getNo());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int deleteProduct(Product pdt) {
		String sql = "DELETE PRODUCT WHERE PRODUCT_NO = ?";
		
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			pstmt.setInt(1, pdt.getNo());
			
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
