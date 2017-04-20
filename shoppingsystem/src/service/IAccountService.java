package service;


import entity.Account;
import exception.AccountException;
import exception.DAOException;


public interface IAccountService {
	public void add(Account acc, int uid) throws DAOException, AccountException ;


	public void AddMoney(double money, int accId) throws DAOException,
			AccountException; 
	
	public void modifyPassword(int accId, String password) throws DAOException,
			AccountException; 

	public void subMoney(double money, int accId) throws DAOException,AccountException ;

	public Account look(int accId) throws DAOException, AccountException;
}
