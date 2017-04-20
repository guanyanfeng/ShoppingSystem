package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.ICartItemDAO;
import utils.ConnectionUtil;
import entity.CartItem;
import exception.DAOException;

public class CartItemDAOImpl implements ICartItemDAO{
	public ArrayList<CartItem> queryByCartId(int cartId) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT CARTITEMID,CARTID,PNAME,PRICE,PNUM FROM T_CARTITEM WHERE CARTID="+cartId;
			rs = stmt.executeQuery(sql);
			ArrayList<CartItem> list = new ArrayList<CartItem>();
			while (rs.next()) {
				int id = rs.getInt(1);
				int oid = rs.getInt(2);
				String name = rs.getString(3);
				double price = rs.getDouble(4);
				int num = rs.getInt(5);
				CartItem item = new CartItem(id, oid, name, price, num);
				list.add(item);
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

	public CartItem queryByItemId(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT CARTITEMID,CARTID,PNAME,PRICE,PNUM FROM T_CARTITEM WHERE CARTITEMID="+id;
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				int i = rs.getInt(1);
				int oid = rs.getInt(2);
				String name = rs.getString(3);
				double price = rs.getDouble(4);
				int num = rs.getInt(5);
				CartItem item = new CartItem(id, oid, name, price, num);
				return item;
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
			String sql = "DELETE FROM T_CARTITEM WHERE CARTITEMID =" + id;
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

	public void update(CartItem item) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_CARTITEM SET CARTID=?,PNAME=?,PRICE=? ,PNUM=? WHERE CartITEMID="
					+ item.getId();
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getCartId());
			pst.setString(2, item.getName());
			pst.setDouble(3, item.getPrice());
			pst.setInt(4, item.getNum());

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

	public void insert(CartItem item) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO T_CARTITEM  VALUES (seq_cartItem.NEXTVAL,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getCartId());
			pst.setString(2, item.getName());
			pst.setDouble(3, item.getPrice());
			pst.setInt(4, item.getNum());
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

	public void deleteByCartId(int cartId) throws DAOException {
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM T_CARTITEM WHERE CARTID =" + cartId;
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
}
