package view;

import java.util.Scanner;
import entity.Account;

public class AccountView {
	private Account account;
	private int accId;
	private double money;
	private String password;
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccId() {
		return accId;
	}
	public void setAccId(int accId) {
		this.accId = accId;
	}

	public void addAccount(){
		System.out.println("输入账户编号");
		accId=Integer.parseInt(getString());
		System.out.println("输入账户密码");
		password=getString();
		System.out.println("请输入充值金额");
		 money=Double.parseDouble(getString());
		
		account = new Account(accId,money,password);
	}
	public void lookAcc(){
		System.out.println("输入账户编号");
		accId=Integer.parseInt(getString());
	}
	public void addMoney(){
		System.out.println("请输入充值金额");
		money=Double.parseDouble(getString());
	}
	public void subMoney(){
		System.out.println("请输入转出金额");
		money=Double.parseDouble(getString());
	}
	public void modifyPassword(){
		System.out.println("输入新账户密码");
		password= getString();
	}
	public void checkPassword(){
		System.out.println("输入支付密码");
		password=getString();
	}
	public void look(Account a){
		System.out.println("账户编号："+a.getAccId()+"\t 余额："+a.getMoney()+"\t");
	}
	private String getString() {
		Scanner sc= new Scanner(System.in);
		return sc.next();
		
	}

}
