package service.impl;

import java.util.ArrayList;
import java.util.Date;

import dao.IAccountDAO;
import dao.IOrderDAO;
import dao.IOrderItemDAO;
import dao.IProductDAO;
import service.IOrderService;
import utils.DateUtils;
import entity.Cart;
import entity.CartItem;
import entity.Order;
import entity.OrderItem;
import entity.Product;
import exception.DAOException;
import exception.OrderException;
import factory.DAOFactory;

public class OrderServiceImpl implements IOrderService {
	public void createOrder(Cart cart) throws DAOException, OrderException {
		IOrderDAO oDAO = DAOFactory.getOrderDAO();
		String date = DateUtils.dateToString("yyyy-MM-dd", new Date());
		Order order = new Order(0, cart.getTotal(), date, null, "δ֧��",
				cart.getUserId());
		oDAO.insert(order);
		int orderId = oDAO.getMaxId();
		IOrderItemDAO iDAO = DAOFactory.getOrderItemDAO();
		IProductDAO pDAO=DAOFactory.getProductDAO();
		for (CartItem ci : cart.getItems()) {
			OrderItem or = new OrderItem(0, orderId, ci.getName(),
					ci.getPrice(), ci.getNum());

			iDAO.insert(or);
			Product p=pDAO.queryByName(ci.getName());
			if(p.getNum()<ci.getNum()){
				throw new OrderException("��治��");
			}
			p.setNum(p.getNum()-ci.getNum());
			pDAO.update(p);
		}
		
		
		

	}

	public ArrayList<Order> lookOrder(int userId) throws DAOException,
			OrderException {
		IOrderDAO oDAO = DAOFactory.getOrderDAO();
		ArrayList<Order> orders = oDAO.queryByUserId(userId);
		if (orders.size() == 0) {
			throw new OrderException("�޶���");
		}
		return orders;
	}

	public void payOrder(int userAccId, int orderId) throws DAOException,
			OrderException {
		IOrderDAO oDAO = DAOFactory.getOrderDAO();
		Order order = oDAO.queryById(orderId);
		if ("��֧��".equals(order.getType())) {
			throw new OrderException("�ö�����֧��");
		}

		double price = order.getTotal();
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		aDAO.addToAdmin(2000, userAccId, price);
		order.setType("��֧��");
		oDAO.update(order);
		/*ArrayList<OrderItem> items = order.getItems();
		IProductDAO pDAO = DAOFactory.getProductDAO();
		for (OrderItem item : items) {
			Product p = pDAO.queryByName(item.getName());
			p.setNum(p.getNum() - item.getNum());
			pDAO.update(p);
		}
*/
	}

	public void back(int userAccId, int orderId) throws DAOException,
			OrderException {
		IOrderDAO oDAO = DAOFactory.getOrderDAO();
		Order order = oDAO.queryById(orderId);
		if ("δ֧��".equals(order.getType())) {
			throw new OrderException("�ö���δ֧�������˵�");
		}
		double price = order.getTotal();
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		aDAO.subFromAdmin(2000, userAccId, price);
		ArrayList<OrderItem> items = order.getItems();
		IProductDAO pDAO = DAOFactory.getProductDAO();
		IOrderItemDAO iDAO = DAOFactory.getOrderItemDAO();
		for (OrderItem item : items) {
			Product p = pDAO.queryByName(item.getName());
			p.setNum(p.getNum() + item.getNum());
			pDAO.update(p);
			iDAO.delete(item.getId());
		}
		aDAO.delete(orderId);
	}

	public void cancelOrder(int orderId) throws DAOException, OrderException {
		IOrderDAO oDAO = DAOFactory.getOrderDAO();
		Order order = oDAO.queryById(orderId);
		if (order == null) {
			throw new OrderException("û�д˶���");
		}
		if ("��֧��".equals(order.getType())) {
			throw new OrderException("�ö�����֧���޷�ȡ��");
		}
		IOrderItemDAO iDAO = DAOFactory.getOrderItemDAO();
		IProductDAO pDAO=DAOFactory.getProductDAO();
		for (OrderItem item : order.getItems()) {
			iDAO.delete(item.getId());
			Product p=pDAO.queryByName(item.getName());
			p.setNum(p.getNum()+item.getNum());
			pDAO.update(p);
		}
		oDAO.delete(orderId);

	}
}
