package factory;

import service.IAccountService;
import service.ICartService;
import service.IOrderService;
import service.IProductService;
import service.IUserService;
import service.impl.AccountServiceImpl;
import service.impl.CartServiceImpl;
import service.impl.OrderServiceImpl;
import service.impl.ProductServiceImpl;
import service.impl.UserServiceImpl;

public   abstract class ServiceFactory {

	public static IUserService getUserService() {
		return new UserServiceImpl();
	}

	public static IAccountService getAccountService() {
		// TODO Auto-generated method stub
		return new AccountServiceImpl();
	}

	public static IProductService getProductService() {
		// TODO Auto-generated method stub
		return new ProductServiceImpl();
	}

	public static ICartService getCartService() {
		// TODO Auto-generated method stub
		return new CartServiceImpl();
	}

	public static IOrderService getOrderService() {
		// TODO Auto-generated method stub
		return new OrderServiceImpl();
	}

}
