package model.dao;

import java.util.List;

import model.entities.Seller;

public interface SellerDao {
	
	void insert (Seller obj); // insere no banco o objeto entre parenteses
	void update (Seller obj);
	void deleteById (Integer id);
	//Retornando uma operacao
	Seller finById(Integer id); // faz a consulta no banco se existir retorna,caso contrario retorna nulo
	
	// findAll retorna todos os departamentos uma lista
	List<Seller> findAll();
	

}
