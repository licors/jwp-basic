package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import core.mvc.Controller;

public class HomeController implements Controller {

	@Override
	public String excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		 request.setAttribute("users", DataBase.findAll());
		 return "index.jsp";
	}
	
}