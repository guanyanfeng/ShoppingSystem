 package factory;

import dao.IAccountDAO;
import dao.ICartDAO;
import dao.ICartItemDAO;
import dao.IOrderDAO;
import dao.IOrderItemDAO;
import dao.IProductDAO;
import dao.IUserDAO;
import dao.impl.AccountDAOImpl;
import dao.impl.CartDAOImpl;
import dao.impl.CartItemDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.UserDAOImpl;

public  abstract class DAOFactory {

	public static IUserDAO getUserDAO() {
		return new UserDAOImpl();
	}

	public static IAccountDAO getAccountDAO() {
		// TODO Auto-generated method stub
		return new AccountDAOImpl();
	}

	public static IProductDAO getProductDAO() {
		// TODO Auto-generated method stub
		return new ProductDAOImpl();
	}

	public static ICartDAO getCartDAO() {
		// TODO Auto-generated method stub
		return new CartDAOImpl();
	}

	public static ICartItemDAO getCartItemDAO() {
		// TODO Auto-generated method stub
		return new CartItemDAOImpl();
	}

	public static IOrderDAO getOrderDAO() {
		// TODO Auto-generated method stub
		return new OrderDAOImpl();
	}

	public static IOrderItemDAO getOrderItemDAO() {
		// TODO Auto-generated method stub
		return new OrderItemDAOImpl();
	}

}
