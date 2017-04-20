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
		System.out.println("输入用户名：");
		name =getString();
		System.out.println("输入密码：");
		password= getString();
	}
	public void register(){
		System.out.println("输入用户名：");
		name =getString();
		System.out.println("输入密码：");
		password= getString();
		System.out.println("输入性别");
		String gender = getString();
		System.out.println("输入年龄");
		int age=Integer.parseInt(getString());
		user=new User(0,name,password,gender,age,null);
	}
	public void modifyPassword(){
		System.out.println("输入新密码");
		password= getString();
	}
	public void modifyUser(){
		System.out.println("输入新年龄");
		int age =Integer.parseInt(getString());
		System.out.println("输入新性别");
		String gender=getString();
		user= new User(0,name,password,gender,age,null);
	}
	private String getString() {
		Scanner sc= new Scanner(System.in);
		return sc.next();
	}
}
