package service;

import entity.Cart;
import entity.CartItem;
import entity.Product;
import exception.CartException;
import exception.DAOException;


public interface ICartService {
	public Cart lookCart(int userId) throws DAOException, CartException ;

	public void modifyNum(int userId, String name, int num)
			throws DAOException, CartException;

	public void addToCart(int userId,Product p,int num) throws DAOException ;

	public void delete(int userId, String name) throws DAOException,
			CartException;

	public void clear(int userId) throws CartException, DAOException;
	
	public void addCart(int userId) throws DAOException;
}
