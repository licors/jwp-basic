package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class LoginController implements Controller {

	@Override
	public String excute(HttpServletRequest reqeust, HttpServletResponse repsonse) throws Exception {
		// TODO Auto-generated method stub
		String userId = reqeust.getParameter("userId");
        String password = reqeust.getParameter("password");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            reqeust.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }

        if (user.matchPassword(password)) {
            HttpSession session = reqeust.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            return "redirect:/";
        } else {
            reqeust.setAttribute("loginFailed", true);
            return "/user/login.jsp";
        }
	}
	
}