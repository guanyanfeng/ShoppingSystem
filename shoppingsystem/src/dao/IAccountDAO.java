package dao;

import java.util.ArrayList;

import entity.Account;

import exception.DAOException;

public interface IAccountDAO {
	public ArrayList<Account> queryAll() throws DAOException ;

	public Account queryById(int id) throws DAOException ;

	public void delete(int id) throws DAOException ;
	
	public void update(Account u) throws DAOException;

	public void insert(Account u,int uid) throws DAOException;

	public void subFromAdmin(int adminAccId,int userAccId,double money) throws DAOException;

	public void addToAdmin(int adminAccId,int userAccId,double money) throws DAOException;

}
