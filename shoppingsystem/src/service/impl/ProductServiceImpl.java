package service.impl;

import java.util.ArrayList;


import service.IProductService;
import dao.IProductDAO;
import entity.Product;
import exception.DAOException;
import exception.ProductException;
import factory.DAOFactory;

public class ProductServiceImpl implements IProductService {
	public void add(Product product) throws DAOException, ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryByName(product.getName());
		if (p != null) {
			throw new ProductException("��Ʒ�Ѵ���");
		}
		pDAO.insert(product);
	}

	public void modifyNum(int id, int num) throws DAOException,
			ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryById(id);
		if (p == null) {
			throw new ProductException("��Ʒ������");
		}
		p.setNum(num);
		pDAO.update(p);
	}

	public void modifyPrice(int id, double price) throws DAOException,
			ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryById(id);
		if (p == null) {
			throw new ProductException("��Ʒ������");
		}
		p.setPrice(price);
		pDAO.update(p);
	}

	public void delete(int id) throws ProductException, DAOException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryById(id);
		if (p == null) {
			throw new ProductException("��Ʒ������");
		}

		pDAO.delete(id);
	}

	public Product lookByName(String name) throws DAOException,
			ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryByName(name);
		if (p == null) {
			throw new ProductException("��Ʒ������");
		}
		return p;
	}

	public Product lookById(int id) throws DAOException, ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		Product p = pDAO.queryById(id);
		if (p == null) {
			throw new ProductException("��Ʒ������");
		}
		return p;
	}

	public ArrayList<Product> LookAll() throws DAOException, ProductException {
		IProductDAO pDAO = DAOFactory.getProductDAO();
		ArrayList<Product> list = pDAO.queryAll();
		if (list.size() == 0) {
			throw new ProductException("����Ʒ");
		}
		return list;
	}

	public ArrayList<Product> LookAll(int page) throws DAOException,
			ProductException {

		IProductDAO pDAO = DAOFactory.getProductDAO();
		int total = pDAO.count();
//		System.out.println(total);
		if (total == 0) {
			throw new ProductException("����Ʒ");
		}
		int num = 5; // ÿҳ��ʾ������
		int start = ((page - 1) * num) + 1; // ÿҳ��ʼ���ݵı��
		int end; // ÿҳĩβ���ݵı��

		int pages = total % num == 0 ? total / num : total / num + 1; // ��ҳ��
		if (pages < page) {
			throw new ProductException("ҳ�볬����Χ��");
		}
		if (pages == page) {
			end = total;
		} else {
			end = page * num;
		}

		ArrayList<Product> list = pDAO.queryAll(start, end);

		return list;
	}
	public int searchPageNum() throws ProductException,DAOException{
		IProductDAO pDAO = DAOFactory.getProductDAO();
		int total = pDAO.count();
		int num = 5; //ÿҳ��ʾ������
		int pages = total%num == 0?total/num:total/num+1; //��ҳ��
		if(total == 0 ){
			throw new ProductException("����Ʒ");
		}
		return pages;
	}

}
