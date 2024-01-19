package model.dao;

import java.util.List;

import model.entities.Department;

public interface DepartmentDao {
	void insert (Department obj); // insere no banco o objeto entre parenteses
	void update (Department obj);
	void deleteById (Integer id);
	//Retornando uma operacao
	Department finById(Integer id); // faz a consulta no banco se existir retorna,caso contrario retorna nulo
	
	// findAll retorna todos os departamentos uma lista
	List<Department> findAll();
	
	
	

}
