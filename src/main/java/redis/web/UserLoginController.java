package redis.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.service.UserLoginService;
import redis.vo.UserInfo;
/**
 * 登录
 * 
 * @param request
 * @param userLogin
 * @return
 * @throws Exception
 */
@RestController
@RequestMapping("/data")
public class UserLoginController {
	
	@Autowired
	private UserLoginService userLoginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public JSONObject userLogin(HttpServletRequest request, UserInfo userLogin) throws Exception {
		
		JSONObject obj = userLoginService.userLogin(request, userLogin);
		
		return obj;
		
	}
	
	@RequestMapping(value = "/findAllUsers", method = RequestMethod.POST)
	public JSONArray findAllUsers() throws Exception {
		
		JSONArray arr = userLoginService.findAllUsers();
		
		return arr;
		
	}
}
