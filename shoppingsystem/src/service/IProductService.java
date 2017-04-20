package service;

import java.util.ArrayList;


import entity.Product;
import exception.DAOException;
import exception.ProductException;

public interface IProductService {
	public void add(Product product) throws DAOException, ProductException;

	public void modifyNum(int id, int num) throws DAOException,
			ProductException;

	public void modifyPrice(int id, double price) throws DAOException,
			ProductException;

	public void delete(int id) throws ProductException, DAOException;

	public Product lookByName(String name) throws DAOException,
			ProductException;

	public ArrayList<Product> LookAll() throws DAOException, ProductException;

	public Product lookById(int id) throws DAOException, ProductException;

	public int searchPageNum() throws ProductException, DAOException;

	public ArrayList<Product> LookAll(int page) throws DAOException,
			ProductException;
}
