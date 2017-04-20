package controller;

import java.util.ArrayList;
import java.util.Scanner;

import service.IAccountService;
import service.ICartService;
import service.IOrderService;
import service.IProductService;
import service.IUserService;
import view.AccountView;
import view.CartView;
import view.OrderView;
import view.ProductView;
import view.UserView;
import entity.Account;
import entity.Cart;
import entity.Order;
import entity.Product;
import exception.AccountException;
import exception.CartException;
import exception.DAOException;
import exception.OrderException;
import exception.ProductException;
import factory.ServiceFactory;

public class UserController {
	ProductView pv = new ProductView();
	IProductService ps = ServiceFactory.getProductService();
	CartView cv = new CartView();
	ICartService cs = ServiceFactory.getCartService();
	OrderView ov=new OrderView();
	IOrderService os = ServiceFactory.getOrderService();
	IAccountService as = ServiceFactory.getAccountService();
	AccountView av = new AccountView();
	private UserView uv = new UserView();
	private IUserService us = ServiceFactory.getUserService();
	private int userId;

	public UserController(int userId) {
		this.userId = userId;
		init();
	}

	private void init() {
		while (true) {
			System.out.println("��1�����2�����빺�ﳵ��3�����붩����4�������˻���5��������Ϣ��-1���˳�");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				shopping();
			}
			if ("2".equals(menu)) {
				enterCart();
			}
			if ("3".equals(menu)) {
				enterOrder();
			}
			if ("4".equals(menu)) {
				enterAccount();
			}
			if ("5".equals(menu)) {
				enterUser();
			}
		}
	}

	private void enterUser() {
		while (true) {
			System.out.println("��1���޸���Ϣ��2���޸����롾-1��������һ��");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				uv.modifyUser();
				try {
					us.modifyUser(userId, uv.getUser());
				} catch (DAOException e) {
					System.out.println(e.getMessage());
				}

			}

			if ("2".equals(menu)) {
				uv.modifyPassword();
				try {
					us.modifyPassword(userId, uv.getPassword());
				} catch (DAOException e) {
					System.out.println(e.getMessage());
				}
			}

		}

	}

	private void enterAccount() {
		while (true) {
			System.out.println("��1���鿴�˻���Ϣ��2����ֵ��3���޸�֧�����롾-1��������һ��");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				av.lookAcc();
				try {
					Account acc = as.look(av.getAccId());
					av.look(acc);
				} catch (DAOException | AccountException e) {
					System.out.println(e.getMessage());
				}

			}

			if ("2".equals(menu)) {
				av.addMoney();
				try {
					System.out.println(av.getMoney());
					as.AddMoney(av.getMoney(), av.getAccId());

				} catch (DAOException | AccountException e) {
					System.out.println(e.getMessage());
				}

			}

			if ("3".equals(menu)) {
				av.modifyPassword();
				try {
					as.modifyPassword(av.getAccId(), av.getPassword());
				} catch (DAOException | AccountException e) {
					System.out.println(e.getMessage());
				}
			}

		}

	}

	private void enterOrder() {
		while (true) {
			System.out.println("��1���鿴������2��ȡ��������3�����4���˿-1��������һ��");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
			try {
				ArrayList<Order> orders=	os.lookOrder(userId);
				ov.look(orders);
			} catch (DAOException | OrderException e) {
				System.out.println(e.getMessage());
			}
			}
			if ("2".equals(menu)) {
				ov.cancel();
				try {
					os.cancelOrder(ov.getoId());
				} catch (DAOException | OrderException e) {
					System.out.println(e.getMessage());
				};
			}
			if ("3".equals(menu)) {
				ov.pay();
				try {
					os.payOrder(ov.getAccId(), ov.getoId());
				} catch (DAOException | OrderException e) {
					System.out.println(e.getMessage());
				}
			}
			if ("4".equals(menu)) {
				ov.back();
				try {
					os.back(ov.getAccId(), ov.getoId());
				} catch (DAOException | OrderException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private void enterCart() {
		while (true) {
			System.out.println("��1���鿴���ﳵ��2��ɾ����Ʒ��3���޸Ĺ���������4����ա�5���µ���-1��������һ��");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				Cart cart;
				try {
					cart = cs.lookCart(userId);
					
					cv.lookCart(cart);
				} catch (DAOException | CartException e) {
					System.out.println(e.getMessage());
				}

			}

			if ("2".equals(menu)) {
				pv.lookByName();
				try {
					cs.delete(userId, pv.getName());
				} catch (DAOException | CartException e) {
					System.out.println(e.getMessage());
				}

			}
			if ("3".equals(menu)) {
				pv.modifyCartNum();
				try {
					cs.modifyNum(userId, pv.getName(), pv.getNum());
				} catch (DAOException | CartException e) {
					System.out.println(e.getMessage());
				}
			}

			if ("4".equals(menu)) {
				try {
					cs.clear(userId);
				} catch (CartException | DAOException e) {
					System.out.println(e.getMessage());
				}
			}
			if ("5".equals(menu)) {
				Cart cart;
				try {
					cart = cs.lookCart(userId);
					os.createOrder(cart);
					cs.clear(userId);
				} catch (DAOException | CartException | OrderException e) {
					System.out.println(e.getMessage());
				}

			}
		}

	}

	private void shopping() {
		while (true) {
			System.out.println("��1���鿴������Ʒ��2��������Ʒ��3�����빺�ﳵ��-1��������һ��");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				try {
					ArrayList<Product> list = ps.LookAll();
					pv.lookAll(list);
				} catch (DAOException | ProductException e) {
					System.out.println(e.getMessage());
				}
			}
			if ("2".equals(menu)) {
				pv.lookByName();

				try {
					Product p = ps.lookByName(pv.getName());
					pv.lookById(p);
				} catch (DAOException | ProductException e) {
					System.out.println(e.getMessage());
				}

			}
			if ("3".equals(menu)) {
				cv.addToCart();
				try {
					Product p = ps.lookById(cv.getProId());
					cs.addToCart(userId, p, cv.getNum());
				} catch (DAOException | ProductException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	private String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}
}
