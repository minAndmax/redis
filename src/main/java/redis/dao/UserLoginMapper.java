package redis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import redis.vo.UserInfo;

@Mapper
public interface UserLoginMapper {

	/**
	 * 用户登录
	 * 
	 * @param userLogin
	 * @return
	 * @throws Exception
	 */
	UserInfo userLogin(UserInfo userLogin) throws Exception;
	
	List<UserInfo> findAllUsers()throws Exception;
	

}
