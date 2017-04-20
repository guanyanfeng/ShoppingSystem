package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{

	private int id;
	private int userId;
	private ArrayList<CartItem> items;
	private double total;
	private String date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public ArrayList<CartItem> getItems() {
		return items;
	}
	public void setItems(ArrayList<CartItem> items) {
		this.items = items;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", items=" + items
				+ ", total=" + total + ", date=" + date + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cart other = (Cart) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(total) != Double
				.doubleToLongBits(other.total))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	public Cart(int id, int userId, ArrayList<CartItem> items, double total,
			String date) {
		super();
		this.id = id;
		this.userId = userId;
		this.items = items;
		this.total = total;
		this.date = date;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
