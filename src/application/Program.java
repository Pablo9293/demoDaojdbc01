package application;



import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// criar o objeto Department
		
		Department obj = new Department(1,"Books");
		
		// criar um objeto para Seller
		
		Seller seller = new Seller(21,"Isis" , "isis@gmail.com", new Date(), 3000.0, obj);
		
		System.out.println(seller);

	}

}
