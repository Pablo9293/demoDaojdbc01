package application;



import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// Instanciando o sellerDao
		
		SellerDao sellerDao = DaoFactory.createSellerDao(); // nao conhece a implemntação, somente a interface
		// Acima e uma injeção de dependencia sem explicitar a implementacao.
		
		
		System.out.println("=== Test 1: seller findById ===");
		// Decalarar o objeto do tipo seller
		
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== Test 2: seller findByDepartment ===");
		Department department = new Department(2, null);
		List<Seller> list = sellerDao.findByDepartment(department);
		for(Seller obj: list) {
			System.out.println(obj);
		}
		System.out.println("\n=== Test 3: seller findAll ===");
		list = sellerDao.findAll();
		for(Seller obj: list) {
			System.out.println(obj);
		}
		System.out.println("\n=== Test 4: seller insert ===");
		Seller newSeller = new Seller(null ," Arthur", "arthur@gmail.com" ,new Date(),6000.0,department);
		//Instanciamos na linha de cima.Na linha de baixo vamos inserir no banco
		sellerDao.insert(newSeller);
		System.out.println("Inserted ! New id = " + newSeller.getId());



	}

}
