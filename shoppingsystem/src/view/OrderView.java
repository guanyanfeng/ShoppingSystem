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
			System.out.println("----�����ţ�" + o.getId() + "----");
			ArrayList<OrderItem> item = o.getItems();
			System.out.println("��Ʒ��\t�۸�\t����\t");
			for (OrderItem i : item) {
				System.out.println(i.getName() + "\t" + i.getPrice() + "\t"
						+ i.getNum());
			}
			System.out.println("--�ܽ�" + o.getTotal());
			System.out.println("--����״̬"+o.getType());
			System.out.println("--���ڣ�" + o.getDate());
		}
	}

	public void pay() {
		System.out.println("����֧���Ķ������");
		oId = Integer.parseInt(getString());
		System.out.println("�����˻����");
		accId = Integer.parseInt(getString());
	}

	public void cancel() {
		System.out.println("����ȡ���Ķ������");
		oId = Integer.parseInt(getString());
	}
	public void back() {
		System.out.println("�����˵����");
		oId = Integer.parseInt(getString());
		System.out.println("���������˻����");
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
