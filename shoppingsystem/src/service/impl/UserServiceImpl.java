package service.impl;

import service.IUserService;
import dao.IUserDAO;
import entity.User;
import exception.DAOException;
import exception.UserExitException;
import exception.UserNameOrPasswordException;
import exception.UserNoExitException;
import factory.DAOFactory;

public class UserServiceImpl implements IUserService {

	public int login(String name,String password) throws DAOException, UserNoExitException, UserNameOrPasswordException{
		IUserDAO uDAO=DAOFactory.getUserDAO();
		User user=uDAO.queryByName(name);
		if(user==null){
			throw new UserNoExitException("用户不存在！");
		}
		if(!password.equals(user.getPassword())){
			throw new UserNameOrPasswordException("用户或密码错误！");
		}
		
		return user.getUserId();
	}
	public int  register(User user) throws DAOException, UserExitException{
		IUserDAO uDAO=DAOFactory.getUserDAO();
		User u=uDAO.queryByName(user.getName());
		if(u!=null){
			throw new UserExitException("用户名已存在");
		}
		uDAO.insert(user);
		return uDAO.queryByName(user.getName()).getUserId();
	}
	public void modifyPassword(int userId,String password) throws DAOException{
		IUserDAO uDAO=DAOFactory.getUserDAO();
		User u=uDAO.queryById(userId);
		u.setPassword(password);
		uDAO.update(u);
	}
	public void modifyUser(int userId,User user) throws DAOException{
		IUserDAO uDAO=DAOFactory.getUserDAO();
		User u=uDAO.queryById(userId);
		u.setAge(user.getAge());
		u.setGender(user.getGender());
		uDAO.update(u);
	}
	

}
