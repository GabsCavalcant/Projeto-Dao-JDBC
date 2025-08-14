package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		;
		
		System.out.println("=== Test 1: Seller Find By id ===");
		System.out.println(seller);
		
		System.out.println("\n === Test 1: Seller Find By Department ===");
		Departament dep = new Departament(3,null);
		List<Seller> list = sellerDao.findByDepartament(dep);
		for ( Seller obj : list) {
			System.out.println(list);
		}
	
	}

}
