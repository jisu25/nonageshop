package nonageshop.service;

import nonageshop.dao.impl.MemberDaoImpl;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Member;

public class MemberService {

	private MemberDaoImpl dao = MemberDaoImpl.getInstance();
	
	public MemberService() {
		dao.setCon(JndiDs.getConnection());
	}
	
	public int confirmId(String id) {
		return dao.confirmId(id);
	}
	
	public Member getMember(String id) {
		return dao.getMember(id);
	}
	
	public int insertMember(Member mem) {
		return dao.insertMember(mem);
	}
}
