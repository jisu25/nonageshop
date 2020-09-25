package nonageshop.dto;

import java.util.Date;

public class Order {

	private int no;
	private Member member;
	private Date orderDate;
	
	public Order() {
	}

	public Order(int no) {
		this.no = no;
	}

	public Order(Member member) {
		this.member = member;
	}

	public Order(int no, Member member) {
		this.no = no;
		this.member = member;
	}

	public Order(int no, Member member, Date orderDate) {
		this.no = no;
		this.member = member;
		this.orderDate = orderDate;
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

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
		return this.no == ((Order) obj).no;
	}

	@Override
	public String toString() {
		return String.format("Order [no=%s, member=%s, orderDate=%s]", no, member, orderDate);
	}
	
	
}
