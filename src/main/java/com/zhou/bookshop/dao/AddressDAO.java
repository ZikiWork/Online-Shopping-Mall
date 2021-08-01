package com.zhou.bookshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.zhou.bookshop.entity.Address;

public interface AddressDAO {

	@Select("select * from shop_address where userid=#{userid}")
	public List<Address> getAddresses(int userid);

	@Select("select * from shop_address where id=#{id}")
	public Address getAddress(int id);

	@Insert("insert into shop_address (userid,reciever,province,city,area,street,phone,telphone,zip,isdef) "
			+ "values(#{user.id},#{reciever},#{province},#{city},#{AREA},#{street},#{phone},#{telphone},#{zip},#{isdef})")
	public void addAddress(Address address);

	@Delete("delete from shop_address where id=#{id}")
	public void delAddress(int id);

	@Update("update shop_address set reciever=#{reciever},province=#{province},city=#{city},area=#{AREA},street=#{street},phone=#{phone},telphone=#{telphone},zip=#{zip},isdef=#{isdef} where id=#{id} ")
	public void updateAddress(Address address);// 更新传入的参数是实体类！！！

	@Update("update shop_address set isdef=#{isdef} where id=#{id}")
	public void updateIsdef(String isdef, int id);// 传入修改参数以及id

	@Update("update shop_address set isdef=#{isdef} where userid=#{userid}")
	public void updateIsdefByUserId(String isdef, int userid);

}
