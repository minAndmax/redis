package redis.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import redis.dao.UserLoginMapper;
import redis.service.UserLoginService;
import redis.util.KeyWord;
import redis.util.MD5Util;
import redis.vo.UserInfo;

@Service(value = "userLoginService")
public class UserLoginServiceImpl implements UserLoginService {

	private static final Logger log = LoggerFactory.getLogger(UserLoginServiceImpl.class);

	@Autowired
	private UserLoginMapper userLoginMapper;
	

	public JSONObject userLogin(HttpServletRequest request,UserInfo userLogin)  {

		JSONObject obj = new JSONObject();

		userLogin.setUserPassword(MD5Util.getMD5(userLogin.getUserPassword()));

		UserInfo user = null;
		try {
			user = userLoginMapper.userLogin(userLogin);
			if (user == null) {

				obj.put("successTip", 0); // 1表示登录成功
				obj.put("successMsg", "登录失败，用户名或密码错误");

			} else {

				obj.put("userName", user.getUserName());
				obj.put("userId", user.getUserId());
				obj.put("roleName", user.getRoleName());

				obj.put("successTip", 1); // 1表示登录成功
				obj.put("successMsg", "登录成功");

				request.getSession().setAttribute(KeyWord.USERSESSION, obj);
			}
			
			log.info("执行了 userLogin: " + obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

	
		return obj;
	}

	@Override
	public JSONArray findAllUsers() throws Exception {
		
		JSONArray arr = new JSONArray();
		List<UserInfo> list = userLoginMapper.findAllUsers();
		for(UserInfo user : list) {
			arr.add(user);
		}
		return arr;
	}

}
