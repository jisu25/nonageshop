package nonageshop.dto;

import java.util.Date;

public class Cart {

	private int no;
	private Member member; // member.id, member.name
	private Product product; // product.no, product.name, product.salePrice
	private int quantity;
	private Date regDate;
		
	public Cart() {
	}
	
	public Cart(int no) {
		this.no = no;
	}

	public Cart(int no, Member member, Product product, int quantity) {
		this.no = no;
		this.member = member;
		this.product = product;
		this.quantity = quantity;
	}
	
	public Cart(int no, Member member, Product product, int quantity, Date regDate) {
		this.no = no;
		this.member = member;
		this.product = product;
		this.quantity = quantity;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
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
		return this.no == ((Cart) obj).no;
	}

	@Override
	public String toString() {
		return String.format("Cart [no=%s, member=%s, product=%s, quantity=%s, regDate=%s]", no, member, product,
				quantity, regDate);
	}
	
	
}
