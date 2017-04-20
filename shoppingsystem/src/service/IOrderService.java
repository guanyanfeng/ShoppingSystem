package service;

import java.util.ArrayList;
import entity.Cart;
import entity.Order;
import exception.DAOException;
import exception.OrderException;


public interface IOrderService {
	public void createOrder(Cart cart) throws DAOException, OrderException ;

	public ArrayList<Order> lookOrder(int userId) throws DAOException,
			OrderException;

	public void payOrder(int userAccId, int orderId) throws DAOException,
			OrderException;

	public void back(int userAccId, int orderId) throws DAOException,
			OrderException ;

	public void cancelOrder(int orderId) throws DAOException, OrderException;

}
