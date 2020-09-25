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
	
	private ProductDaoImpl() {
		con = JndiDs.getConnection();
	}
	
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
		
		String kind = null;
		
		try{
			kind = rs.getString("PRODUCT_KIND");
		} catch(SQLException e) {
		}
		
		int price = rs.getInt("PRICE");
		int salePrice = rs.getInt("SALE");
		
		int margin = 0;
		String content = null;
		String image = null;
		try {
			margin = rs.getInt("MARGIN");
			content = rs.getString("PRODUCT_CONTENT");
			image = rs.getString("PRODUCT_IMAGE");
		} catch(SQLException e) {
		}
		
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

	@Override
	public int totalRecord(String productName) {
		String sql = "SELECT COUNT(*) FROM PRODUCT WHERE PRODUCT_NAME LIKE ?";
		int total_pages = 0;
		
		try(PreparedStatement pstmt = con.prepareStatement(sql)) {
			
			if(productName.equals("")) {
				pstmt.setString(1,  "%%");
			} else {
				pstmt.setString(1, "%" + productName + "%");
			}
			
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					total_pages = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return total_pages;
	}

	private static int VIEW_ROWS = 5;
	private static int COUNTS = 5;
	
	@Override
	public String pageNumber(int tpage, String name) {
		StringBuilder sb = new StringBuilder("");
		
		int total_pages = totalRecord(name);
		int page_count = (total_pages / COUNTS) + 1; 
		
		if(total_pages % COUNTS == 0) {
			page_count--;
		}
		
		if(tpage < 1) {
			tpage = 1;
		}
		
		int start_page = tpage - (tpage % VIEW_ROWS) + 1;
		int end_page = start_page + (COUNTS - 1);
		
		if (end_page > page_count) {
			end_page = page_count;
		}
		
		if (start_page > VIEW_ROWS) {
			sb.append("<a href='adminProductList.do?page=1&key=" + name + "'>&lt;&lt;</a>&nbsp;&nbsp;");
			sb.append("<a href='adminProductList.do?tpage=" + (start_page - 1));
			sb.append("&key={$product_name}'>&lt;</a>&nbsp;&nbsp;");
		}
		
		for(int i = start_page; i <= end_page; i++) {
			if(i == tpage) {
				sb.append("<font color=red>[" + i + "]</a>&nbsp&nbsp;");
			} else {
				sb.append("<a href='adminProductList.do?tpage=" + i + "&key=" + name + "'>[" + i + "]</a>&nbsp&nbsp;");
			}
		}
		
		if(page_count > end_page) {
			sb.append("<a href='adminProductList.do?tpage=" + (end_page + 1) + "&key=" + name +"'> &g; </a>&nbsp;&nbsp;");
			sb.append("<a href='adminProductList.do?tpage=" + page_count + "&key=" + name +"'> &g; </a>&nbsp;&nbsp;");
		}
		
		return sb.toString();
	}

	
	@Override
	public ArrayList<Product> listProduct(int tpage, String productName) {
		
		String sql = "SELECT PRODUCT_NO, PRODUCT_NAME, PRODUCT_KIND, PRICE, SALE, REG_DATE, DEL_YN, BEST_YN "
				+ "FROM PRODUCT WHERE PRODUCT_NAME LIKE ? ORDER BY PRODUCT_NO DESC";
		
		int absolutePage = 1;
		absolutePage = (tpage - 1) * COUNTS + 1;
		ArrayList<Product> productList = new ArrayList<Product>();
		
		System.out.println("listProduct() con : " + con);
		try(PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
			pstmt.setString(1, "%" + productName + "%");
			try(ResultSet rs = pstmt.executeQuery()) {
				if(rs.next()) {
					rs.absolute(absolutePage);
					int count = 0;
					while(count < COUNTS) {
						productList.add(getProduct(rs));
						if(rs.isLast()) break;
						rs.next();
						count++;
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return productList;
	}
	
}
