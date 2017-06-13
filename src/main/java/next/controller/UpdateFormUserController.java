package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class UpdateFormUserController implements Controller {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse repsonse) throws Exception {
		// TODO Auto-generated method stub
		String userId = request.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(request.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }
        request.setAttribute("user", user);
        return "/user/updateForm.jsp";
	}

}
