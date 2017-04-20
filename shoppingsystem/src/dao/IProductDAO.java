package dao;

import java.util.ArrayList;

import entity.Product;
import exception.DAOException;

public interface IProductDAO {
	public ArrayList<Product> queryAll() throws DAOException;

	public ArrayList<Product> queryAll(int start, int end) throws DAOException;

	public Product queryById(int id) throws DAOException;

	public void delete(int id) throws DAOException;

	public void update(Product product) throws DAOException;

	public void insert(Product product) throws DAOException;

	public Product queryByName(String name) throws DAOException;
	
	public int count() throws DAOException;

}
