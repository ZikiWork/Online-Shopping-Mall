package com.zhou.bookshop.entity;

public class Address {
	private int id;
	private User user;
	private String reciever;
	private String province;
	private String city;
	private String AREA;
	private String street;
	private String phone;
	private String telphone;
	private String isdef;
	private String zip;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReciever() {
		return reciever;
	}

	public void setReciever(String reciever) {
		this.reciever = reciever;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAREA() {
		return AREA;
	}

	public void setAREA(String aREA) {
		AREA = aREA;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getIsdef() {
		return isdef;
	}

	public void setIsdef(String isdef) {
		this.isdef = isdef;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", reciever=" + reciever + ", province=" + province + ", city=" + city + ", AREA="
				+ AREA + ", street=" + street + ", phone=" + phone + ", telphone=" + telphone + ", isdef=" + isdef
				+ ", zip=" + zip + "]";
	}
}
