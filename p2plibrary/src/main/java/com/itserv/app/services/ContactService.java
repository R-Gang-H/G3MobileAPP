package com.itserv.app.services;

import com.itserv.app.model.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑 模拟从服务端获取数据
 * 
 */
public class ContactService {
	public List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact(34, "张三", "15170013856"));
		contacts.add(new Contact(39, "李四", "15170013856"));
		contacts.add(new Contact(67, "王五", "15170013856"));
		return contacts;
	}
}