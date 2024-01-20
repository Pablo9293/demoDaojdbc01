package model.dao;

import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	
	// Instanciar os daos
	
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC();
	}

}
