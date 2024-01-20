package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	
	void insert (Seller obj); // insere no banco o objeto entre parenteses
	void update (Seller obj);
	void deleteById (Integer id);
	//Retornando uma operacao
	Seller findById(Integer id); // faz a consulta no banco se existir retorna,caso contrario retorna nulo
	
	// findAll retorna todos os departamentos uma lista
	List<Seller> findAll();
	//buscar por departamento
	List<Seller>findByDepartment(Department department); // Assinatura do metodo
	

}
