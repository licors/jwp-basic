package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller{
	private String forwardUrl;
	
	public ForwardController(String forwardUrl) {
		// TODO Auto-generated constructor stub
		if(forwardUrl == null) {
			throw new NullPointerException("forwardUrl is null");
		}
		this.forwardUrl = forwardUrl;
	}
	
	@Override
	public String excute(HttpServletRequest reqeust, HttpServletResponse repsonse) throws Exception {
		// TODO Auto-generated method stub
		return forwardUrl;
	}

}
