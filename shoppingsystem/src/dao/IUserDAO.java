package dao;


import java.util.ArrayList;

import entity.User;
import exception.DAOException;

public interface IUserDAO {
	public ArrayList<User> queryAll() throws DAOException ;

	public User queryByName(String name) throws DAOException ;

	public void delete(int id) throws DAOException ;
	
	public void update(User u) throws DAOException;

	public void insert(User u) throws DAOException;
	public User queryById(int id) throws DAOException;
}
