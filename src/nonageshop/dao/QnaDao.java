package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Qna;

public interface QnaDao {

	ArrayList<Qna> selectQnaById(String id);
	
	Qna selectQnaByNo(int no);
	
	int insertQna(Qna qna, String sessionId);
	
}
