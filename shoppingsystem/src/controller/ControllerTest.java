package controller;

import java.util.Scanner;

import service.IAccountService;
import service.ICartService;
import service.IUserService;
import view.AccountView;
import view.UserView;
import exception.AccountException;
import exception.DAOException;
import exception.UserExitException;
import exception.UserNameOrPasswordException;
import exception.UserNoExitException;
import factory.ServiceFactory;

public class ControllerTest {
	private UserView uv = new UserView();
	private IUserService us = ServiceFactory.getUserService();
	private IAccountService as = ServiceFactory.getAccountService();
	private AccountView av = new AccountView();
	private ICartService cs=ServiceFactory.getCartService();
	private int userId;
	private boolean is=false;

	public ControllerTest() {
		init1();
		
		if (is) {
			init2();
		}
	}

	public static void main(String[] args) {
		ControllerTest test = new ControllerTest();
	}

	private void init2() {
		if (uv.getName().equals("admin")) {
			AdminController adminController = new AdminController(userId);
		} else {
			UserController userController = new UserController(userId);
		}

	}

	private void init1() {

		System.out.println("--¡¾1¡¿µÇÂ¼¡¾2¡¿×¢²á--");
		String menu = getString();
		if ("1".equals(menu)) {
			uv.login();
			try {
				userId = us.login(uv.getName(), uv.getPassword());
				is = true;
			} catch (DAOException | UserNoExitException
					| UserNameOrPasswordException e) {
				System.out.println(e.getMessage());
			}
		}
		if ("2".equals(menu)) {
			uv.register();
			try {
				userId = us.register(uv.getUser());
				av.addAccount();
				as.add(av.getAccount(), userId);
				cs.addCart(userId);
				is = true;
			} catch (DAOException | UserExitException | AccountException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private String getString() {
		Scanner sc = new Scanner(System.in);
		return sc.next();
	}

}
