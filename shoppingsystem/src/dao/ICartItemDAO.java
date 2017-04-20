package dao;

import java.util.ArrayList;

import entity.CartItem;
import exception.DAOException;

public interface ICartItemDAO {
	public ArrayList<CartItem> queryByCartId(int cartId) throws DAOException;

	public CartItem queryByItemId(int id) throws DAOException;

	public  void delete(int id) throws DAOException;

	public void update(CartItem item) throws DAOException;

	public void insert(CartItem item) throws DAOException;

	public void deleteByCartId(int cartId) throws DAOException;

}
