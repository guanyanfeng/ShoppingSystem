package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import utils.ConnectionUtil;
import dao.IAccountDAO;
import entity.Account;
import exception.DAOException;

public class AccountDAOImpl implements IAccountDAO {

	@Override
	public ArrayList<Account> queryAll() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT ACCOUNTID,MONEY,APASSWORD FROM T_ACCOUNT ";
			rs = stmt.executeQuery(sql);
			ArrayList<Account> list = new ArrayList<Account>();
			while (rs.next()) {
				int aid = rs.getInt(1);
				double money = rs.getDouble(2);
				String password = rs.getString(3);
				Account account = new Account(aid, money, password);
				list.add(account);
			}
			return list;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}

		}
	}

	@Override
	public Account queryById(int id) throws DAOException {
		ArrayList<Account> list = queryAll();
		for (Account acc : list) {
			if (id == acc.getAccId())
				return acc;
		}
		return null;
	}

	@Override
	public void delete(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM T_ACCOUNT WHERE ACCOUNTID =" + id;
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				ConnectionUtil.close(conn, stmt);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}

		}

	}

	@Override
	public void update(Account u) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_ACCOUNT SET MONEY=?,APASSWORD=? WHERE ACCOUNTID="
					+ u.getAccId();
			pst = con.prepareStatement(sql);
			pst.setDouble(1, u.getMoney());
			pst.setString(2, u.getPassword());

			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				ConnectionUtil.close(con, pst);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	public void insert(Account acc, int uid) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO T_ACCOUNT  VALUES (?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, acc.getAccId());
			pst.setInt(2, uid);
			pst.setDouble(3, acc.getMoney());
			pst.setString(4, acc.getPassword());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				ConnectionUtil.close(con, pst);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	public void addToAdmin(int adminAccId,int userAccId,double money) throws DAOException{
		Connection  conn = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		try {
			conn=ConnectionUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="UPDATE T_ACCOUNT SET MONEY = MONEY +? WHERE ACCOUNTID=?";
			 pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setInt(2, adminAccId);
			
			String sql2="UPDATE T_ACCOUNT SET MONEY = MONEY -? WHERE ACCOUNTID=?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setDouble(1, money);
			pstmt2.setInt(2, userAccId);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e.getMessage());
			}
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close1(conn,pstmt,pstmt2);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		
	}

	public void subFromAdmin(int adminAccId,int userAccId,double money) throws DAOException{
		Connection  conn = null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		try {
			conn=ConnectionUtil.getConnection();
			conn.setAutoCommit(false);
			String sql="UPDATE T_ACCOUNT SET MONEY = MONEY -? WHERE ACCOUNTID=?";
			 pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, money);
			pstmt.setInt(2, adminAccId);
			
			String sql2="UPDATE T_ACCOUNT SET MONEY = MONEY +? WHERE ACCOUNTID=?";
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setDouble(1, money);
			pstmt2.setInt(2, userAccId);
			pstmt.executeUpdate();
			pstmt2.executeUpdate();
			
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new DAOException(e.getMessage());
			}
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close1(conn,pstmt,pstmt2);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		
	}
}
