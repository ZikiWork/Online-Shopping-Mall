package com.zhou.bookshop.service;

import java.util.List;

import com.zhou.bookshop.entity.Address;

public interface AddressService {

	public List<Address> getAddresses(int userid);

	public Address getAddress(int id);

	public void addAddress(int userid, Address address);

	public void delAddress(int id);

	public void updateAddress(Address address, int userid);

	public void updateIsdef(String isdef, int id);

	public void setDefAddress(int userid, int addressid, String isdef);

}
