package application;

import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.dao.Implements.DepartmenDaoImplementsJdbc;
import model.entities.Departament;

public class Program2 {

	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		
		DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
		
		System.out.println("=== TEST 1: findById =======");
		Departament dep = departamentoDao.findById(1);
		System.out.println(dep);
		
		
		System.out.println("\n=== TEST 2: findAll =======");
		
		List<Departament> list = departamentoDao.findAll();
		for (Departament i : list) {
			System.out.println(i);
		}
		
		System.out.println("\n === Test 3: Department Insert ===");

		
		Departament newDepartment = new Departament (null, "limao");
		departamentoDao.insert(newDepartment);
		
		System.out.println("Insert!! new id +  " + newDepartment.getId());
		
		System.out.println("\n === Test 5: Department Update ===");
		Departament dep2 = departamentoDao.findById(5);
		dep2.setName("Joaozao");
		departamentoDao.update(dep2);
		System.out.println(dep2);
		
		System.out.println("\n === Test 5: Department Delete ===");
		System.out.println("Choose id for delete : ");		
		int id = teclado.nextInt();
		departamentoDao.deleteById(id);
		System.out.println("Id: " + id + " delete complete!");
		
		
		
		
		
		
		
	}

}
