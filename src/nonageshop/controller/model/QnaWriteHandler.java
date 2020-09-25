package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.Qna;
import nonageshop.service.QnaService;

public class QnaWriteHandler implements Command {
	
	private QnaService service;
	
	public QnaWriteHandler() {
		service = new QnaService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("QnaWriteHandler >>");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("loginForm.do");
			return null;
		} else {
			if(request.getMethod().equalsIgnoreCase("GET")) {
				return "qna/qnaWrite.jsp";
			} else {
				Qna qna = new Qna();
				qna.setSubject(request.getParameter("subject"));
				qna.setContent(request.getParameter("content"));
				System.out.println(qna);
				System.out.println(loginUser.getId());
				
				int res = service.addQna(qna, loginUser.getId());
				if (res == 1) {
					response.sendRedirect("qnaList.do");
					System.out.println("글쓰기 완료");
				}
				return null;
			}
		}
	}

}
