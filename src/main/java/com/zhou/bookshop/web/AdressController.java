package com.zhou.bookshop.web;

import java.util.List;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zhou.bookshop.entity.Address;
import com.zhou.bookshop.entity.User;
import com.zhou.bookshop.service.AddressService;

@Controller
@RequestMapping("address")
public class AdressController {
	protected static Logger logger = LoggerFactory.getLogger(AdressController.class);

	@Autowired
	private AddressService addressService;

	// 增加地址！
	@PostMapping("add")
	@ResponseBody
	public String Address(@RequestBody Address address, HttpSession session) {
		User user = (User) session.getAttribute("user");
		address.setUser(user);
		addressService.addAddress(user.getId(), address);
		return "add";
	}

	// 查询地址！
	@GetMapping("getaddress")
	@ResponseBody // 返回JSON数据
	public List<Address> Address(HttpSession session) {
		User user = (User) session.getAttribute("user");
//		return addressService.getAddresses(user.getId());// 返回JSON数组
		List<Address> addresses = addressService.getAddresses(user.getId());
		return addresses;
	}

	// 删除地址
	@GetMapping("deleteaddress/{addressId}")
	@ResponseBody // 返回单个数据（也属于JSON）
	public String deleteaddress(@PathVariable int addressId) {// 获取数据
		addressService.delAddress(addressId);
		return "del";
	}

	// 根据前台传过来的ID，根据ID获取地址信息：以便显示于增加地址框处！
	@GetMapping("updateaddress/{addressId}")
	@ResponseBody // 返回数据
	public Address updateaddress(@PathVariable int addressId) {// 获取请求数据
		return addressService.getAddress(addressId);
	}

	// 得到前台更新后的数据 执行地址更新！
	@PostMapping("updateAddress")
	@ResponseBody
	public String updateAddress(@RequestBody Address address, HttpSession session) {
		User user = (User) session.getAttribute("user");
		System.out.println(address.getIsdef());
		addressService.updateAddress(address, user.getId());
		return "YES";
	}

	// 修改默认地址状态
	@GetMapping("updateIsdef/{addressId}/{isdef}")
	@ResponseBody // 返回数据
	public String updateIsdef(@PathVariable int addressId, @PathVariable String isdef, HttpSession session) {// 获取数据
		User user = (User) session.getAttribute("user");
		addressService.setDefAddress(user.getId(), addressId, isdef);
		return "updateIsdef";
	}
}
