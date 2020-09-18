package nonageshop.dto;

import java.util.Date;

public class Member {
	
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String zipNum;
	private String address;
	private String phone;
	private String delYn;
	private Date regDate;
	
	
	public Member() {
	}
	
	public Member(String id) {
		this.id = id;
	}
	
	public Member(String id, String pwd) {
		this.id = id;
		this.pwd = pwd;
	}
	
	public Member(String id, String pwd, String name, String email, String zipNum, String address, String phone) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.zipNum = zipNum;
		this.address = address;
		this.phone = phone;
	}
	
	public Member(String id, String pwd, String name, String email, String zipNum, String address, String phone,
			String delYn, Date regDate) {
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.zipNum = zipNum;
		this.address = address;
		this.phone = phone;
		this.delYn = delYn;
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZipNum() {
		return zipNum;
	}

	public void setZipNum(String zipNum) {
		this.zipNum = zipNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
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
				"Member [id=%s, pwd=%s, name=%s, email=%s, zipNum=%s, address=%s, phone=%s, delYn=%s, regDate=%s]", id,
				pwd, name, email, zipNum, address, phone, delYn, regDate);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((Member)obj).id;
	}
	
	
}
