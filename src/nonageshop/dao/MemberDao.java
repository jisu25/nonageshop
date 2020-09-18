package nonageshop.dao;

import nonageshop.dto.Member;

public interface MemberDao {

	int confirmId(String id);
	
	Member getMember(String id);
	
	int insertMember(Member mem);
	
}
