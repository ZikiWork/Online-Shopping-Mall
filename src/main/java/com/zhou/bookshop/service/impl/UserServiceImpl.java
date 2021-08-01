package com.zhou.bookshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.bookshop.dao.UserDAO;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDAO.addUser(user);
	}

	@Override
	public User getUserByCondition(String account, String pwssword) {
		// TODO Auto-generated method stub
		return userDAO.getUserByCondition(account, pwssword);
	}

	/**
	 * 判断账号是否存在： 存在返回 TRUE
	 */
	@Override
	public Boolean isExist(String account) {
		return userDAO.isExist(account) != 0;
	}

	@Override
	public void updateUserPhoto(String avatat, int id) {
		// TODO Auto-generated method stub
		userDAO.updateUserPhoto(avatat, id);
	}

	@Override
	public User getUserByid(int id) {
		// TODO Auto-generated method stub
		return userDAO.getUserByid(id);
	}

}
