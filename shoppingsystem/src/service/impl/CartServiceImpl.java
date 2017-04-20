package service.impl;

import java.util.ArrayList;
import java.util.Date;

import dao.ICartDAO;
import dao.ICartItemDAO;
import entity.Cart;
import entity.CartItem;
import entity.Product;
import exception.CartException;
import exception.DAOException;
import factory.DAOFactory;
import service.ICartService;
import utils.DateUtils;

public class CartServiceImpl implements ICartService {
	public Cart lookCart(int userId) throws DAOException, CartException {
		ICartDAO cDAO = DAOFactory.getCartDAO();
		Cart cart = cDAO.queryByUserId(userId);
		if (cart == null) {
			throw new CartException("购物车无商品");
		}
		return cart;
	}
	public void addCart(int userId) throws DAOException{
		String date= DateUtils.dateToString("yyyy-MM-dd", new Date());
		Cart cart = new Cart(0,userId,null,0,date);
		ICartDAO cDAO=DAOFactory.getCartDAO();
		cDAO.insert(cart);
	}

	public void modifyNum(int userId, String name, int num)
			throws DAOException, CartException {
		ICartDAO cDAO = DAOFactory.getCartDAO();
		Cart cart = cDAO.queryByUserId(userId);
		int cartId = cart.getId();
		ICartItemDAO iDAO = DAOFactory.getCartItemDAO();
		ArrayList<CartItem> items = iDAO.queryByCartId(cartId);
		if (items.size() == 0) {
			throw new CartException("购物车为空");
		}
		CartItem item = null;
		for (CartItem i : items) {
			if (name.equals(i.getName())) {
				item = i;
			}
		}
		if (item == null) {
			throw new CartException("该商品不存在");
		}
		iDAO.delete(item.getId());
		cart.setTotal(cart.getTotal()-item.getPrice()*item.getNum()+item.getPrice()*num);
		cDAO.update(cart);
		item.setNum(num);
		iDAO.update(item);
	}

	public void addToCart(int userId,Product p ,int num) throws DAOException {
		
		ICartDAO cDAO = DAOFactory.getCartDAO();
		Cart cart = cDAO.queryByUserId(userId);

		int cartId = cart.getId();
		
		CartItem item=new CartItem(0,cartId,p.getName(),p.getPrice(),num);
		ICartItemDAO iDAO = DAOFactory.getCartItemDAO();
		ArrayList<CartItem> items = iDAO.queryByCartId(cartId);
		int x=0;
		if (items.size() != 0) {
		
			for (CartItem i : items) {
				if (item.getName().equals(i.getName())) {
					i.setNum(i.getNum()+item.getNum());
					iDAO.update(i);
					x=1;
				}
			}
			
			
		}
		if(x==0){
			iDAO.insert(item);
		}
		double total=cart.getTotal()+item.getPrice()*item.getNum();
		cart.setTotal(total);
		cDAO.update(cart);
		

	}

	public void delete(int userId, String name) throws DAOException,
			CartException {
		ICartDAO cDAO = DAOFactory.getCartDAO();
		Cart cart = cDAO.queryByUserId(userId);
		int cartId = cart.getId();
		ICartItemDAO iDAO = DAOFactory.getCartItemDAO();
		ArrayList<CartItem> items = iDAO.queryByCartId(cartId);
		if (items.size() == 0) {
			throw new CartException("购物车为空");
		}
		CartItem item = null;
		for (CartItem i : items) {
			if (name.equals(i.getName())) {
				item = i;
			}
		}
		if (item == null) {
			throw new CartException("该商品不存在");
		}
		iDAO.delete(item.getId());
		cart.setTotal(cart.getTotal()-item.getPrice()*item.getNum());
		cDAO.update(cart);
	}

	public void clear(int userId) throws CartException, DAOException {

		ICartDAO cDAO = DAOFactory.getCartDAO();
		Cart cart = cDAO.queryByUserId(userId);
		int cartId = cart.getId();
		ICartItemDAO iDAO = DAOFactory.getCartItemDAO();
		iDAO.deleteByCartId(cartId);
		cart.setTotal(0);
		cDAO.update(cart);
	}
}
