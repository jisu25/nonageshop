package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;

public class LoginHandler implements Command {

	private MemberService service;
	
	public LoginHandler() {
		service = new MemberService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("login.do >> GET");
			
			request.setAttribute("id", request.getAttribute("id"));
			
			return "/member/login.jsp";
		} else {
			
			String url = "member/login_fail.jsp";
			
			System.out.println("login.do >> POST");
			
			HttpSession session = request.getSession();

			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			Member mem = service.getMember(id);
			System.out.println("mem 조회 : " + mem);
			System.out.println("입력한 비번 : " + pwd);

			if(mem != null && pwd.equals(mem.getPwd())) {
				session.removeAttribute("id");
				session.setAttribute("loginUser", mem);
				response.sendRedirect(request.getContextPath() + "/index.do");
				return null;
			} 
			
			return url;
		}
	}

}
