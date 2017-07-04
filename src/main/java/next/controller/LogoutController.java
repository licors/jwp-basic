package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.mvc.Controller;

public class LogoutController implements Controller {
	
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse repsonse) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
        session.removeAttribute(UserSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
}