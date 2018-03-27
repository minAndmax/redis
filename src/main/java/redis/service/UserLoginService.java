package redis.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.vo.UserInfo;

public interface UserLoginService {

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	JSONObject userLogin(HttpServletRequest request,UserInfo userLogin) throws Exception;
	
	JSONArray findAllUsers()throws Exception;

}
