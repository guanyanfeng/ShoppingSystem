package service.impl;

import service.IAccountService;
import dao.IAccountDAO;
import entity.Account;
import exception.AccountException;
import exception.DAOException;
import factory.DAOFactory;

public class AccountServiceImpl implements IAccountService {
	public void add(Account acc, int uid) throws DAOException, AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(acc.getAccId());
		if (a != null) {
			throw new AccountException("�˻��Ѵ���");
		}
		aDAO.insert(acc, uid);

	}

	public void AddMoney(double money, int accId) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null)
			throw new AccountException("�˻�������");

		a.setMoney(money + a.getMoney());
		aDAO.update(a);
	}

	public void modifyPassword(int accId, String password) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null)
			throw new AccountException("�˻�������");

		a.setPassword(password);
		aDAO.update(a);
	}

	public void subMoney(double money, int accId) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null) {
			throw new AccountException("�˻�������");
		}
		if (a.getMoney() < money) {
			throw new AccountException("����");
		}
		a.setMoney(a.getAccId() - money);
		aDAO.update(a);
	}
	
	public Account look(int accId) throws DAOException, AccountException{
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if(a==null){
			throw new AccountException("�˻�������");
		}
		return a;
	}
}
