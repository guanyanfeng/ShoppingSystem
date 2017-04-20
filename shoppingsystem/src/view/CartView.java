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
		System.out.println("输入商品编号");
		proId = Integer.parseInt(getString());
		System.out.println("输入购买数量");
		num = Integer.parseInt(getString());
	}

	private String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

	public void lookCart(Cart cart) {
		System.out.println("----购物车编号：" + cart.getId() + "----");
		ArrayList<CartItem> item = cart.getItems();
		System.out.println("商品名\t价格\t数量");
		if (item.size() != 0) {
			for (CartItem i : item) {
				System.out.println(i.getName() + "\t" + i.getPrice() + "\t"
						+ i.getNum());
			}
		}
		System.out.println("--总金额：" + cart.getTotal());
		System.out.println("--日期：" + cart.getDate());
	}

}
