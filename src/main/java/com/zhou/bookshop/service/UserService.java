package com.zhou.bookshop.service;

import com.zhou.bookshop.entity.User;

public interface UserService {

	public void addUser(User user);

	public User getUserByCondition(String account, String pwssword);

	public Boolean isExist(String account);

	public void updateUserPhoto(String avatat, int id);
	
	public User getUserByid(int id);
}
