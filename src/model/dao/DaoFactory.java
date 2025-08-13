package model.dao;

import model.dao.Implements.SellerDaoImplementsJdbc;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoImplementsJdbc();
	}

}
