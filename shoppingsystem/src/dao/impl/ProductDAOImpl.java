package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utils.ConnectionUtil;
import dao.IProductDAO;
import entity.Product;
import exception.DAOException;

public class ProductDAOImpl implements IProductDAO {

	public ArrayList<Product> queryAll() throws DAOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT PID,PNAME,PRICE,PNUM FROM T_PRODUCT ";
			rs = stmt.executeQuery(sql);
			ArrayList<Product> list = new ArrayList<Product>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				double price = rs.getDouble(3);
				int num = rs.getInt(4);
				Product product = new Product(id, name, price, num);
				list.add(product);
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
	
	public int count() throws DAOException{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql = "select count(PID) from t_product";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return rs.getInt(1);
			}
			return 0;
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

	public ArrayList<Product> queryAll(int start, int end) throws DAOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "select *from (select rownum rn,e.* from(select *from t_product) e)where rn between ? and ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			ArrayList<Product> list = new ArrayList<Product>();
			while (rs.next()) {
				int id = rs.getInt("PID");
				String name = rs.getString("PNAME");
				double price = rs.getDouble("PRICE");
				int num = rs.getInt("PNUM");
				Product product = new Product(id, name, price, num);
				list.add(product);
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

	public Product queryById(int id) throws DAOException {
		ArrayList<Product> list = queryAll();
		for (Product p : list) {
			if (id == p.getId())
				return p;
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
			String sql = "DELETE FROM T_PRODUCT WHERE PID =" + id;
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
	public void update(Product product) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_PRODUCT SET PNAME=?,PRICE=? ,PNUM=? WHERE PID="
					+ product.getId();
			pst = con.prepareStatement(sql);
			pst.setString(1, product.getName());
			pst.setDouble(2, product.getPrice());
			pst.setInt(3, product.getNum());
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

	@Override
	public void insert(Product product) throws DAOException {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "INSERT INTO T_PRODUCT  VALUES (seq_product.nextval,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, product.getName());
			pst.setDouble(2, product.getPrice());
			pst.setInt(3, product.getNum());
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

	public Product queryByName(String name) throws DAOException {
		ArrayList<Product> list = queryAll();
		for (Product p : list) {
			if (name.equals(p.getName()))
				return p;
		}
		return null;
	}
}
