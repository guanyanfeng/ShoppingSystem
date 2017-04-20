package service;

import entity.User;
import exception.DAOException;
import exception.UserExitException;
import exception.UserNameOrPasswordException;
import exception.UserNoExitException;

public interface IUserService {
	
	public int   login(String name,String password) throws DAOException, UserNoExitException, UserNameOrPasswordException;
	public int  register(User user) throws DAOException, UserExitException;
	public void modifyPassword(int userId,String password) throws DAOException;
	public void modifyUser(int userId,User user) throws DAOException;

}
