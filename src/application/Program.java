package application;



import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		// Instanciando o sellerDao
		
		SellerDao sellerDao = DaoFactory.createSellerDao(); // nao conhece a implemntação, somente a interface
		// Acima e uma injeção de dependencia sem explicitar a implementacao.
		
		// Decalarar o objeto do tipo seller
		
		Seller seller = sellerDao.finById(3);
		System.out.println(seller);

	}

}
