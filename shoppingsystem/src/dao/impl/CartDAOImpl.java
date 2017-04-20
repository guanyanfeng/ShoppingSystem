 package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConnectionUtil;
import dao.ICartDAO;
import entity.Cart;
import entity.CartItem;
import exception.DAOException;

public class CartDAOImpl implements ICartDAO {
	public ArrayList<Cart> queryAll() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select c.*,i.*  from t_cart  c left join t_CartItem  i on  c.cartid=i.cartid";
			rs = stmt.executeQuery(sql);
			ArrayList<Cart> list = new ArrayList<Cart>();
			while (rs.next()) {
				int cartid = rs.getInt(1);
				int userid = rs.getInt(2);
				double total = rs.getDouble(3);
				String d = rs.getString(4);
				int cartItemId = rs.getInt(5);
				String pname = rs.getString(7);
				double price = rs.getDouble(8);
				int pnum = rs.getInt(9);
				CartItem item = new CartItem(cartItemId, cartid, pname, price,
						pnum);
				Cart cart = new Cart(cartid, userid, null, total, d);
				if (list.contains(cart)) {
					for (Cart c : list) {
						if (c.equals(cart)) {
							ArrayList<CartItem> itemList = c.getItems();
							itemList.add(item);
						}
					}
				} else {
					ArrayList<CartItem> items = new ArrayList<CartItem>();
					items.add(item);
					cart.setItems(items);
					list.add(cart);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			// 5.¹Ø±Õ×ÊÔ´
			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}

	}

	public Cart queryById(int cartId) throws DAOException {
		ArrayList<Cart> list = queryAll();
		for (Cart o : list) {
			if (cartId == o.getId())
				return o;
		}
		return null;
	}

	public void insert(Cart cart) throws DAOException {
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql1 = "INSERT INTO T_CART VALUES (seq_cart.nextval,?,?,?)";
			pst = conn.prepareStatement(sql1);
			pst.setInt(1, cart.getUserId());
			pst.setDouble(2, cart.getTotal());
			pst.setString(3, cart.getDate());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {
			try {
				ConnectionUtil.close(conn, pst);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}

	public void update(Cart cart) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_CART SET TOTAL=?,cartdate =? WHERE CARTID="
					+ cart.getId();
			pst = con.prepareStatement(sql);
			pst.setDouble(1, cart.getTotal());
			pst.setString(2, cart.getDate());

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

	public void delete(int id) throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "delete from t_cart where cartid =" + id;
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

	public int getMaxId() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select max(cartId) cartId from t_cart";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				int orderId = rs.getInt("cartId");
				return orderId;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} finally {

			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return -1;
	}

	public Cart queryByUserId(int userId) throws DAOException{
		
		ArrayList<Cart> list = queryAll();
		for (Cart o : list) {
			if (userId == o.getUserId())
				return o;
		}
		return null;
	}
}
