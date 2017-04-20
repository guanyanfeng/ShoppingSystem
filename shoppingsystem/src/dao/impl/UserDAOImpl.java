package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.IUserDAO;
import utils.ConnectionUtil;
import entity.Account;
import entity.User;
import exception.DAOException;

public class UserDAOImpl implements IUserDAO {
	public ArrayList<User> queryAll() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U JOIN T_ACCOUNT A ON U.USERID=U.USERID";
			rs = stmt.executeQuery(sql);
			ArrayList<User> list = new ArrayList<User>();
			while (rs.next()) {
				int uid = rs.getInt(1);
				String name = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				int age = rs.getInt(5);
				User user = new User(uid, name, password, gender, age, null);
				int aid = rs.getInt(6);
				double money = rs.getDouble(7);
				String apassword = rs.getString(8);
				Account account = new Account(aid, money, apassword);
				user.setAccount(account);
				list.add(user);
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

	public User queryByName(String name) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U LEFT JOIN T_ACCOUNT A ON U.USERID=U.USERID WHERE U.UNAME='"
					+ name + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int uid = rs.getInt(1);
				String uname = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				int age = rs.getInt(5);
				User user = new User(uid, uname, password, gender, age, null);
				int aid = rs.getInt(6);
				double money = rs.getDouble(7);
				String apassword = rs.getString(8);
				Account account = new Account(aid, money, apassword);
				user.setAccount(account);
				return user;
			}
			return null;
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
	public User queryById(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT U.USERID UI,U.UNAME UN,U.UPASSWORD UP,U.GENDER UG,U.UAGE UA, A.ACCOUNTID AI,A.MONEY AM,A.APASSWORD AP FROM T_USER U LEFT JOIN T_ACCOUNT A ON U.USERID=U.USERID WHERE U.userid="
					+id;
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int uid = rs.getInt(1);
				String uname = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);
				int age = rs.getInt(5);
				User user = new User(uid, uname, password, gender, age, null);
				int aid = rs.getInt(6);
				double money = rs.getDouble(7);
				String apassword = rs.getString(8);
				Account account = new Account(aid, money, apassword);
				user.setAccount(account);
				return user;
			}
			return null;
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

	public void delete(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from t_user where userid =" + id;
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

	public void update(User u) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_USER SET UNAME=?,UPASSWORD=?,UAGE=?,GENDER=? WHERE USERID="
					+ u.getUserId();
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getName());
			pst.setString(2, u.getPassword());
			pst.setInt(3, u.getAge());
			pst.setString(4, u.getGender());
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

	public void insert(User u) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "insert into T_user values (seq_user.nextval,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, u.getName());
			pst.setString(2, u.getPassword());
			pst.setInt(3, u.getAge());
			pst.setString(4, u.getGender());
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
}
