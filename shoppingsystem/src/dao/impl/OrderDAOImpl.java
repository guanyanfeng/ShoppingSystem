package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;



import utils.ConnectionUtil;
import dao.IOrderDAO;
import entity.Order;
import entity.OrderItem;
import exception.DAOException;

public class OrderDAOImpl implements IOrderDAO{
	public ArrayList<Order> queryAll() throws DAOException{
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs  = null; 
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql ="select o.*,i.*  from t_order  o  join t_orderItem  i on  o.orderid=i.orderid";
			rs = stmt.executeQuery(sql);
			ArrayList<Order> list = new ArrayList<Order>();
			while(rs.next()){
				int orderid = rs.getInt(1);
				int userid = rs.getInt(2);
				double total = rs.getDouble(3);
				String d = rs.getString(4);
				String type = rs.getString(5);
				
				int itemId = rs.getInt(6);
				String pname = rs.getString(8);
				double price = rs.getDouble(9);
				int pnum = rs.getInt(10);
				OrderItem item = new OrderItem(itemId,orderid,pname,price,pnum);
				Order order = new Order(orderid,total,d,null,type,userid);
				if(list.contains(order)){
					for(Order o : list){
						if(o.equals(order)){
						   ArrayList<OrderItem> itemList = o.getItems();
						   itemList.add(item);
						}
					}
				}else{
					ArrayList<OrderItem> items = new ArrayList<OrderItem>();
					items.add(item);
					order.setItems(items);
					list.add(order);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		
	}

	public ArrayList<Order> queryByUserId(int userId) throws DAOException{
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs  = null; 
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql ="select o.*,i.*  from t_order  o  join t_orderItem  i on  o.orderid=i.orderid where o.userId="+userId;
			rs = stmt.executeQuery(sql);
			ArrayList<Order> list = new ArrayList<Order>();
			while(rs.next()){
				int orderid = rs.getInt(1);
				int userid = rs.getInt(2);
				double total = rs.getDouble(3);
				String d = rs.getString(4);
				String type = rs.getString(5);
				
				int itemId = rs.getInt(6);
				String pname = rs.getString(8);
				double price = rs.getDouble(9);
				int pnum = rs.getInt(10);
				OrderItem item = new OrderItem(itemId,orderid,pname,price,pnum);
				Order order = new Order(orderid,total,d,null,type,userid);
				if(list.contains(order)){
					for(Order o : list){
						if(o.equals(order)){
						   ArrayList<OrderItem> itemList = o.getItems();
						   itemList.add(item);
						}
					}
				}else{
					ArrayList<OrderItem> items = new ArrayList<OrderItem>();
					items.add(item);
					order.setItems(items);
					list.add(order);
				}
			}
			return list;
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			//5.¹Ø±Õ×ÊÔ´
			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		
	}
	public Order queryById(int orderId) throws DAOException{
		ArrayList<Order> list=queryAll();
		for(Order o:list){
			if(orderId==o.getId())
				return o;
		}
		return null;
	}
	public void insert(Order order) throws DAOException{
		Connection conn = null;
		PreparedStatement  pst = null; 
		try {
			conn = ConnectionUtil.getConnection();	
			String sql1="INSERT INTO T_ORDER VALUES (seq_order.nextval,?,?,?,?)";
			pst=conn.prepareStatement(sql1);
			pst.setInt(1, order.getUserId());
			pst.setDouble(2, order.getTotal());
			pst.setString(3, order.getDate());
			pst.setString(4, order.getType());
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close(conn, pst);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}
	
	public void update(Order order) throws DAOException{
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = ConnectionUtil.getConnection();
			String sql = "UPDATE T_ORDER SET TOTAL=?,ODATE=?,OTYPE=? WHERE ORDERID="
					+ order.getId();
			pst = con.prepareStatement(sql);
			pst.setDouble(1, order.getTotal());
			pst.setString(2, order.getDate());
			pst.setString(3, order.getType());

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
	
	
	public void delete(int id) throws DAOException{
		Connection conn = null;
		Statement stmt = null; 
		try {
			conn = ConnectionUtil.getConnection();
			stmt  = conn.createStatement();
			String sql ="delete from t_order where orderid ="+id;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close(conn, stmt);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
	}
	

	public int getMaxId() throws DAOException {
		Connection conn = null;
		Statement stmt  = null;
		ResultSet rs  = null; 
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			String sql ="select max(orderId) orderId from t_order";
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				int orderId = rs.getInt("orderId");
				return orderId;
			}
		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		}finally{
			try {
				ConnectionUtil.close(conn, stmt, rs);
			} catch (SQLException e) {
				throw new DAOException(e.getMessage());
			}
		}
		return -1;
	}

}
