package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class ProfileController implements Controller {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse repsonse) throws Exception {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }
        request.setAttribute("user", user);
        return "/user/profile.jsp";
	}
}
