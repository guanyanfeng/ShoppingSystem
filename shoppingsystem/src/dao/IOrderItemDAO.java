package dao;

import java.util.ArrayList;


import entity.OrderItem;
import exception.DAOException;

public interface IOrderItemDAO {
	public ArrayList<OrderItem> queryOrderId(int orderId) throws DAOException ;

	public OrderItem queryByItemId(int id) throws DAOException ;

	public void delete(int id) throws DAOException;

	public void update(OrderItem item) throws DAOException ;

	public void insert(OrderItem item) throws DAOException;

	public void deleteByOrderId(int orderId) throws DAOException;


}
