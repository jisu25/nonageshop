package nonageshop.controller.model.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Worker;
import nonageshop.service.WorkerService;

public class AdminLoginHandler implements Command {

	private WorkerService service;
	
	public AdminLoginHandler() {
		service = new WorkerService();
	}
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO 관리자 메인페이지로 이동
		
		String url = "admin/main.jsp";
		String msg ="";
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("AdminLoginHandler >> GET");
			
			return url;
		} else {
			System.out.println("AdminLoginHandler >> POST");
			
			String id = request.getParameter("workerId").trim();
			String pwd = request.getParameter("workerPwd").trim();
			
			Worker worker = new Worker(id, pwd);
			int res = service.workerCheck(worker);
			
			if(res == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("workerId", id);
				url = "adminProductList.do";
				response.sendRedirect(url);
			} else if (res == 0) {
				msg = "아이디 또는 비밀번호를 확인하세요.";
			}
			
			request.setAttribute("message", msg);
			return null;
		}
	}

}
