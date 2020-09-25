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

public class QnaViewHandler implements Command {

	private QnaService service;
	
	public QnaViewHandler() {
		service = new QnaService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		System.out.println("QnaViewHandler >>");
		
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			response.sendRedirect("loginForm.do");
			return null;
		} else {
			int no = Integer.parseInt(request.getParameter("no"));
			Qna qna = service.getQnaByNo(no);
			
			request.setAttribute("qna", qna);
			return "qna/qnaView.jsp";
		}
	}

}
