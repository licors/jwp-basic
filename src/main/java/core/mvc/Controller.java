package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	String excute(HttpServletRequest request, HttpServletResponse repsonse)
		throws Exception;
}
