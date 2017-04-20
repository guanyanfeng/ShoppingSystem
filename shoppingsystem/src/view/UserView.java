package view;

import java.util.Scanner;

import entity.User;

public class UserView {
	private String name;
	private String password;
	private User user;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void login(){
		System.out.println("�����û�����");
		name =getString();
		System.out.println("�������룺");
		password= getString();
	}
	public void register(){
		System.out.println("�����û�����");
		name =getString();
		System.out.println("�������룺");
		password= getString();
		System.out.println("�����Ա�");
		String gender = getString();
		System.out.println("��������");
		int age=Integer.parseInt(getString());
		user=new User(0,name,password,gender,age,null);
	}
	public void modifyPassword(){
		System.out.println("����������");
		password= getString();
	}
	public void modifyUser(){
		System.out.println("����������");
		int age =Integer.parseInt(getString());
		System.out.println("�������Ա�");
		String gender=getString();
		user= new User(0,name,password,gender,age,null);
	}
	private String getString() {
		Scanner sc= new Scanner(System.in);
		return sc.next();
	}
}
