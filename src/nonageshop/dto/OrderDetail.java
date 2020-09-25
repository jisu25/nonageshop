package nonageshop.dto;

public class OrderDetail {

	private int no;
	private Order order;
	private Product product;
	private int quantity;
	private String resYn;
	
	public OrderDetail() {
	}

	public OrderDetail(int no) {
		this.no = no;
	}

	public OrderDetail(int no, Order order, Product product, int quantity, String resYn) {
		this.no = no;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.resYn = resYn;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getResYn() {
		return resYn;
	}

	public void setResYn(String resYn) {
		this.resYn = resYn;
	}

	@Override
	public String toString() {
		return String.format("OrderDetail [no=%s, order=%s, product=%s, quantity=%s, resYn=%s]", no, order, product,
				quantity, resYn);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + no;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.no == ((OrderDetail) obj).no;
	}
	
}
