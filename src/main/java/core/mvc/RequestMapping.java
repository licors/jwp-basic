package core.mvc;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import next.controller.CreateUserController;
import next.controller.HomeController;

public class RequestMapping {
	private static final Logger logger = LoggerFactory.getLogger(RequestMapping.class);
	private Map<String , Controller> mappings = new HashMap<>();
	
	void initMapping() {
		mappings.put("/", new HomeController());
		mappings.put("/users/form", new ForwardController("/user/form.jsp"));
		mappings.put("/users/loginForm", new ForwardController("/user/login.jsp"));
		mappings.put("/users/create", new CreateUserController());
		//다른 컨트롤러 들 추가하라
		logger.info("Initialized Request Mapping!");
	}
	
	public Controller findController(String url) {
		return mappings.get(url);
	}
	
	public void put(String url, Controller controller) {
		mappings.put(url, controller);
	}
}
