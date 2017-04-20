package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConnectionUtil;
import dao.IOrderItemDAO;
import entity.OrderItem;
import exception.DAOException;

public class OrderItemDAOImpl implements IOrderItemDAO {

	public ArrayList<OrderItem> queryOrderId(int orderId) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT ITEMID,ORDERID,PNAME,PRICE,PNUM FROM T_ORDERITEM WHERE ORDERID="+orderId;
			rs = stmt.executeQuery(sql);
			ArrayList<OrderItem> list = new ArrayList<OrderItem>();
			while (rs.next()) {
				int id = rs.getInt(1);
				int oid = rs.getInt(2);
				String name = rs.getString(3);
				double price = rs.getDouble(4);
				int num = rs.getInt(5);
				OrderItem item = new OrderItem(id, oid, name, price, num);
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

	public OrderItem queryByItemId(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT ITEMID,ORDERID,PNAME,PRICE,PNUM FROM T_ORDERITEM WHERE ITEMID="+id;
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				int i = rs.getInt(1);
				int oid = rs.getInt(2);
				String name = rs.getString(3);
				double price = rs.getDouble(4);
				int num = rs.getInt(5);
				OrderItem item = new OrderItem(i, oid, name, price, num);
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
			String sql = "DELETE FROM T_ORDERITEM WHERE ITEMID =" + id;
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

	public void update(OrderItem item) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_ORDERITEM SET ORDERID=?,PNAME=?,PRICE=? ,PNUM=? WHERE ITEMID="
					+ item.getId();
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getOrderId());
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

	public void insert(OrderItem item) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO T_ORDERITEM  VALUES (seq_orderItem.NEXTVAL,?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getOrderId());
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

	public void deleteByOrderId(int orderId) throws DAOException{
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "DELETE FROM T_ORDERITEM WHERE ORDERID =" +orderId;
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
