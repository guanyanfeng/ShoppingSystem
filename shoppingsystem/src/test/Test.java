package test;

import service.IProductService;
import view.ProductView;
import exception.DAOException;
import exception.ProductException;
import factory.ServiceFactory;

public class Test {
	public static void main(String[] args) throws DAOException {
		/*UserDAOImpl u=new UserDAOImpl();
		User  user= new User(1000,"enen","666666","nan",20,null);
		u.insert(user);
		u.delete(1001);
		u.update(user);*/
		ProductView pv= new ProductView();
		IProductService ps=ServiceFactory.getProductService();
		try {
			
			pv.lookpage(ps.LookAll(1));
			int pages=ps.searchPageNum();
			pv.pages(pages);
			pv.page();
		pv.lookpage(ps.LookAll(pv.getPage()));
		} catch (ProductException e) {
			System.out.println(e.getMessage());
		}
	}

}
