package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;
import nonageshop.service.MemberService;

public class JoinHandler implements Command {

	private MemberService service;
	
	public JoinHandler() {
		service = new MemberService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("join.do >> GET");
			
			return "/member/join.jsp";
		} else {
			System.out.println("join.do >> POST");

			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			String zipNum = request.getParameter("zipNum");
			String address = request.getParameter("addr1") + " " + request.getParameter("addr2");
			String phone = request.getParameter("phone");
			
			Member mem = new Member(id, pwd, name, email, zipNum, address, phone);
			int res = service.insertMember(mem);
			
			request.getSession().setAttribute("id", id);
			response.sendRedirect(request.getContextPath() + "/loginForm.do");
			
			return null;
		}
	}

}
