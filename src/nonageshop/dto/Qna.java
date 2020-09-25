package nonageshop.dto;

import java.util.Date;

public class Qna {
	private int no;
	private String subject;
	private String content;
	private String reply;
	private Member member;
	private String resYn;
	private Date regDate;
	
	public Qna() {
	}
	
	public Qna(int no) {
		this.no = no;
	}
	
	public Qna(int no, String subject, String content, Member member) {
		this.no = no;
		this.subject = subject;
		this.content = content;
		this.member = member;
	}
	
	public Qna(int no, String subject, String content, String reply, Member member, String resYn, Date regDate) {
		this.no = no;
		this.subject = subject;
		this.content = content;
		this.reply = reply;
		this.member = member;
		this.resYn = resYn;
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getResYn() {
		return resYn;
	}

	public void setResYn(String resYn) {
		this.resYn = resYn;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return String.format("Qna [no=%s, subject=%s, content=%s, reply=%s, member=%s, resYn=%s, regDate=%s]", no,
				subject, content, reply, member, resYn, regDate);
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
		return this.no == ((Qna) obj).no;
	}
	
}
