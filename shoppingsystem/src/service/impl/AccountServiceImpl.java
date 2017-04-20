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
			throw new AccountException("账户已存在");
		}
		aDAO.insert(acc, uid);

	}

	public void AddMoney(double money, int accId) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null)
			throw new AccountException("账户不存在");

		a.setMoney(money + a.getMoney());
		aDAO.update(a);
	}

	public void modifyPassword(int accId, String password) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null)
			throw new AccountException("账户不存在");

		a.setPassword(password);
		aDAO.update(a);
	}

	public void subMoney(double money, int accId) throws DAOException,
			AccountException {
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if (a == null) {
			throw new AccountException("账户不存在");
		}
		if (a.getMoney() < money) {
			throw new AccountException("余额不足");
		}
		a.setMoney(a.getAccId() - money);
		aDAO.update(a);
	}
	
	public Account look(int accId) throws DAOException, AccountException{
		IAccountDAO aDAO = DAOFactory.getAccountDAO();
		Account a = aDAO.queryById(accId);
		if(a==null){
			throw new AccountException("账户不存在");
		}
		return a;
	}
}
