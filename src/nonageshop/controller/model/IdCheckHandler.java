package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.MemberService;

public class IdCheckHandler implements Command {

	private MemberService service;
	
	public IdCheckHandler() {
		service = new MemberService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("idCheck.do >> GET");
			
			request.getParameter("id");
			return "/member/idcheck.jsp";
		} else {
			System.out.println("idCheck.do >> POST");
			
			String id = request.getParameter("id");
			int message = service.confirmId(id);
			
			request.setAttribute("id", id);
			request.setAttribute("message", message);
			
			return "/member/idcheck.jsp";
		}
	}

}
