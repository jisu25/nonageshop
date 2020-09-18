package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;

public class LoginFormHandler implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("login.do >> GET");
			
			return "/member/login.jsp";
		} else {
			System.out.println("login.do >> POST");
			
			request.getSession().removeAttribute("id");
			request.getSession().invalidate();
			
			return "/member/login.jsp";
		}
	}

}
