package dao;

import java.util.ArrayList;

import entity.Cart;

import exception.DAOException;

public interface ICartDAO {
	public ArrayList<Cart> queryAll() throws DAOException;

	public void insert(Cart cart) throws DAOException;

	public void update(Cart cart) throws DAOException;

	public void delete(int id) throws DAOException;

	public int getMaxId() throws DAOException;

	public Cart queryByUserId(int userId) throws DAOException;
}
