package com.zhou.bookshop.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhou.bookshop.entity.User;

public interface UserDAO {
	@Insert("insert into shop_user (account,password,email,avatat,score,repassword) values( #{account},#{password},#{email},'photo',0,#{repassword})")
	@Options(useGeneratedKeys = true, keyProperty = "id")
	public void addUser(User user);

	@Select("select * from shop_user where account=#{param1} and password=#{param2}")
	public User getUserByCondition(String account, String password);

	@Select("select count(*) from shop_user where account=#{account}")
	public int isExist(String account);

	@Update("update shop_user set avatat=#{avatat} where id=#{id}")
	public void updateUserPhoto(String avatat, int id);
	
	@Select("select * from shop_user where id=#{id}")
	public User getUserByid(int id);
}
