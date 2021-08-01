package com.zhou.bookshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhou.bookshop.dao.AddressDAO;
import com.zhou.bookshop.entity.Address;
import com.zhou.bookshop.service.AddressService;

@Service
public class AddressSreviceImpl implements AddressService {

	@Autowired
	private AddressDAO addressDAO;

	@Override
	public List<Address> getAddresses(int userid) {
		// TODO Auto-generated method stub
		return addressDAO.getAddresses(userid);
	}

	@Override
	public void addAddress(int userid, Address address) {
		/**
		 * 增加时确保：默认收获地址只能有一个！ 如果默认地址复选框选中，则更新所有收货地址的isdef状态！
		 */
		if (address.getIsdef().equals("1")) {
			List<Address> Addresses = addressDAO.getAddresses(userid);
			String def = "0";
			for (Address Address : Addresses) {
				addressDAO.updateIsdef(def, Address.getId());
			}
		}
		addressDAO.addAddress(address);
	}

	@Override
	public void delAddress(int id) {
		addressDAO.delAddress(id);
	}

	@Override
	public void updateAddress(Address address, int userid) {
		System.out.println(address.getIsdef());

		/**
		 * 更新时确保：默认收获地址只能有一个！ 如果默认地址复选框选中，则更新所有收货地址的isdef状态！
		 */
		if (address.getIsdef().equals("1")) {
			String def = "0";
			addressDAO.updateIsdefByUserId(def, userid);// 更具User的id更新默认收货地址状态！
		}
		addressDAO.updateAddress(address);
	}

	@Override
	public Address getAddress(int id) {
		// TODO Auto-generated method stub
		return addressDAO.getAddress(id);
	}

	@Override
	public void updateIsdef(String isdef, int id) {
		// TODO Auto-generated method stub
		addressDAO.updateIsdef(isdef, id);
	}

	/**
	 * 在所有地址栏上修改默认地址状态： 选中一个地址为默认地址时。其他的地址改变默认状态！
	 */
	@Override
	public void setDefAddress(int userid, int addressid, String isdef) {
		// TODO Auto-generated method stub
		List<Address> addresses = addressDAO.getAddresses(userid);
		String def = "0";
		for (Address address : addresses) {
			addressDAO.updateIsdef(def, address.getId());
		}
		addressDAO.updateIsdef(isdef, addressid);
	}

}
