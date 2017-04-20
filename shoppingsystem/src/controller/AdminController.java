package controller;

import java.util.ArrayList;
import java.util.Scanner;

import service.IAccountService;
import service.IOrderService;
import service.IProductService;
import service.IUserService;
import view.AccountView;
import view.OrderView;
import view.ProductView;
import view.UserView;
import entity.Account;
import entity.Product;
import exception.AccountException;
import exception.DAOException;
import exception.ProductException;
import factory.ServiceFactory;

public class AdminController {
	ProductView pv = new ProductView();
	IProductService ps = ServiceFactory.getProductService();
	private UserView uv = new UserView();
	private IUserService us = ServiceFactory.getUserService();
	private int adminId;
	IAccountService as = ServiceFactory.getAccountService();
	AccountView av = new AccountView();
	public AdminController(int id) {
		this.adminId = id;
		init();
	}

	private void init() {
		while (true) {
			System.out.println("【1】管理商品【2】进入账户【3】个人信息【-1】退出");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				enterProduct();
			}
			if ("2".equals(menu)) {
				enterAccount();
			}
			
			if ("3".equals(menu)) {
				enterAdmin();
			}
		}

	}

	private void enterAdmin() {
		while (true) {
			System.out.println("【1】修改信息【2】修改密码【-1】返回上一级");
			String menu = getString();
			if ("-1".equals(menu))
				break;
			if ("1".equals(menu)) {
				uv.modifyUser();
				try {
					us.modifyUser(adminId, uv.getUser());
				} catch (DAOException e) {
					System.out.println(e.getMessage());
				}

			}

			if ("2".equals(menu)) {
				uv.modifyPassword();
				try {
					us.modifyPassword(adminId, uv.getPassword());
				} catch (DAOException e) {
					System.out.println(e.getMessage());
				}
			}

		}

	}



	private void enterAccount() {
		while (true) {
			System.out.println("【1】查看账户信息【2】充值【3】修改支付密码【-1】返回上一级");
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

	private void enterProduct() {
		while (true) {
			System.out.println("【1】查看所有商品【2】添加商品【3】修改价格【4】修改库存【-1】返回上一级");
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
				pv.add();
				try {
					ps.add(pv.getProduct());
				} catch (DAOException | ProductException e) {
					System.out.println(e.getMessage());
				}
			}
			if ("3".equals(menu)) {
				pv.modifyPrice();
				try {
					ps.modifyPrice(pv.getpId(), pv.getPrice());
				} catch (DAOException | ProductException e) {
					System.out.println(e.getMessage());

				}
			}
			if ("4".equals(menu)) {
				pv.modifyNum();
				try {
					ps.modifyNum(pv.getpId(), pv.getNum());
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
