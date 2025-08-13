package model.dao;

import db.DB;
import model.dao.Implements.SellerDaoImplementsJdbc;

public class DaoFactory {
	
	public static SellerDao createSellerDao() {
		return new SellerDaoImplementsJdbc(DB.getConnection());
	}

}
