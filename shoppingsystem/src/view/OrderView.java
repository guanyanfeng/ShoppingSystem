package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Order;
import entity.OrderItem;

public class OrderView {
	private int oId;
	private int accId;

	public void look(ArrayList<Order> orders) {
		for (Order o : orders) {
			System.out.println("----订单号：" + o.getId() + "----");
			ArrayList<OrderItem> item = o.getItems();
			System.out.println("商品名\t价格\t数量\t");
			for (OrderItem i : item) {
				System.out.println(i.getName() + "\t" + i.getPrice() + "\t"
						+ i.getNum());
			}
			System.out.println("--总金额：" + o.getTotal());
			System.out.println("--订单状态"+o.getType());
			System.out.println("--日期：" + o.getDate());
		}
	}

	public void pay() {
		System.out.println("输入支付的订单编号");
		oId = Integer.parseInt(getString());
		System.out.println("输入账户编号");
		accId = Integer.parseInt(getString());
	}

	public void cancel() {
		System.out.println("输入取消的订单编号");
		oId = Integer.parseInt(getString());
	}
	public void back() {
		System.out.println("输入退单编号");
		oId = Integer.parseInt(getString());
		System.out.println("输入退入账户编号");
		accId = Integer.parseInt(getString());
	}
	private String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public int getoId() {
		return oId;
	}

	public void setoId(int oId) {
		this.oId = oId;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}
}
