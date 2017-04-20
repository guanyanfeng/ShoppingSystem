package view;

import java.util.ArrayList;
import java.util.Scanner;

import entity.Cart;
import entity.CartItem;

public class CartView {
	private int proId;
	private int num;

	public int getProId() {
		return proId;
	}

	public void setProId(int proId) {
		this.proId = proId;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public void addToCart() {
		System.out.println("������Ʒ���");
		proId = Integer.parseInt(getString());
		System.out.println("���빺������");
		num = Integer.parseInt(getString());
	}

	private String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public void lookCart(Cart cart) {
		System.out.println("----���ﳵ��ţ�" + cart.getId() + "----");
		ArrayList<CartItem> item = cart.getItems();
		System.out.println("��Ʒ��\t�۸�\t����");
		if (item.size() != 0) {
			for (CartItem i : item) {
				System.out.println(i.getName() + "\t" + i.getPrice() + "\t"
						+ i.getNum());
			}
		}
		System.out.println("--�ܽ�" + cart.getTotal());
		System.out.println("--���ڣ�" + cart.getDate());
	}

}
