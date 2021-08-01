package com.zhou.bookshop.entity;

public class UUID {

	public static void main(String[] args) {
		String uuid = java.util.UUID.randomUUID().toString(); // 获取UUID并转化为String对象
		System.err.println(uuid);
		uuid = uuid.replace("-", ""); // 因为UUID本身为32位只是生成时多了“-”，所以将它们去点就可
		System.err.println(uuid);
	}

}
