package nonageshop.dto;

import java.util.Date;

public class Product {

	private int no;
	private String name;
	private String kind;
	private int price;
	private int salePrice;
	private int margin;
	private String content;
	private String image;
	private String delYn; // 삭제된 상태: 'y', 사용 가능: 'n'
	private String bestYn; // 베스트 상품: 'y', 일반: 'n'
	private Date regDate;
	
	public Product() {
	}

	public Product(int no) {
		this.no = no;
	}

	public Product(int no, String name, int salePrice, String image) {
		this.no = no;
		this.name = name;
		this.salePrice = salePrice;
		this.image = image;
	}

	public Product(int no, String name, String kind, int price, int salePrice, int margin, String content, String image,
			String delYn, String bestYn) {
		this.no = no;
		this.name = name;
		this.kind = kind;
		this.price = price;
		this.salePrice = salePrice;
		this.margin = margin;
		this.content = content;
		this.image = image;
		this.delYn = delYn;
		this.bestYn = bestYn;
	}

	public Product(int no, String name, String kind, int price, int salePrice, int margin, String content, String image,
			String delYn, String bestYn, Date regDate) {
		this.no = no;
		this.name = name;
		this.kind = kind;
		this.price = price;
		this.salePrice = salePrice;
		this.margin = margin;
		this.content = content;
		this.image = image;
		this.delYn = delYn;
		this.bestYn = bestYn;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getBestYn() {
		return bestYn;
	}

	public void setBestYn(String bestYn) {
		this.bestYn = bestYn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return String.format(
				"Product [no=%s, name=%s, kind=%s, price=%s, salePrice=%s, margin=%s, content=%s, image=%s, delYn=%s, bestYn=%s, regDate=%s]",
				no, name, kind, price, salePrice, margin, content, image, delYn, bestYn, regDate);
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
		return this.no == ((Product) obj).no;
	}
	
	
}
