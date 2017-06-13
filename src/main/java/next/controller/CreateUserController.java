package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.db.DataBase;
import core.mvc.Controller;
import next.model.User;

public class CreateUserController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateUserController.class);
    
	@Override
	public String excute(HttpServletRequest request, HttpServletResponse reponse) throws Exception {
		// TODO Auto-generated method stub
	    User user = new User(request.getParameter("userId"), request.getParameter("password"), request.getParameter("name"),
                request.getParameter("email"));
        log.debug("User : {}", user);

        DataBase.addUser(user);

        return "redirect:/";
	}
	
}
