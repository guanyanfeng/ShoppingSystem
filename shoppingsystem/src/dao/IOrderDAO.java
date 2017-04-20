package dao;

import java.util.ArrayList;
import entity.Order;
import exception.DAOException;

public interface IOrderDAO {
	public ArrayList<Order> queryAll() throws DAOException;

	public Order queryById(int orderId) throws DAOException;

	public void insert(Order order) throws DAOException;

	public void update(Order order) throws DAOException;

	public void delete(int id) throws DAOException;

	public int getMaxId() throws DAOException;
	public ArrayList<Order> queryByUserId(int userId) throws DAOException;

}
