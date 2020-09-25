package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.impl.QnaDaoImpl;
import nonageshop.ds.JndiDs;
import nonageshop.dto.Qna;

public class QnaService {

	private QnaDaoImpl dao = QnaDaoImpl.getInstance();
	
	public QnaService() {
		dao.setCon(JndiDs.getConnection());
	}
	
	public ArrayList<Qna> getQnasById(String id) {
		return dao.selectQnaById(id);
	}
	
	public Qna getQnaByNo(int no) {
		return dao.selectQnaByNo(no);
	}
	
	public int addQna(Qna qna, String sessionId) {
		return dao.insertQna(qna, sessionId);
	}
	
}
