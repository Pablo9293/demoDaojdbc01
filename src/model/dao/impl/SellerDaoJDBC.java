package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {
	// conexao
	private Connection conn;

	// Vamos forcar a injeção de dependencia com o construtor
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn; // Dependencia
	}

	@Override
	public void insert(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON  seller.DepartmentId = department.Id " + "WHERE seller .Id = ?");
			st.setInt(1, id);// id da linha 33
			rs = st.executeQuery();
			if (rs.next()) { // Nao tiver reultado sai do if e retorna nulo. Se tiver
				Department dep = instantiateDepartment(rs); // chamada da funcao
				// Criando o objeto seller que vai apontar para o departamento

				Seller obj = instantiateSeller(rs, dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); // Excessao personalizada
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id")); // nome da coluna que esta no banco
		obj.setName(rs.getString("Name")); // nome da coluna esta no banco
		obj.setEmail(rs.getString("Email"));// nome da coluna esta no banco
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep); // Associacao de objetos montado
		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department(); // variavel temporaria
		dep.setId(rs.getInt("DepartmentId")); // Nome da coluna que esta no banco
		dep.setName(rs.getString("DEpName")); // Nome do departament no banco
		return dep;

	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
				    +"ORDER BY NAME "); 
					
			st.setInt(1, department.getId());// id da linha 33
			rs = st.executeQuery();
			//declarar lista de resultados
			
			List<Seller> list = new ArrayList<>();
			// para nao duplicar o departamento a cada interação do while
			Map<Integer,Department> map = new HashMap<>();
			
			while (rs.next()) { 
				// Testa se o departamento ja existe
				Department dep = map.get(rs.getInt("DepartmentId")); // se o departamento for nulo,sera instanciado
				if(dep == null) { // significa que ainda nao existe
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				
				// Criando o objeto seller que vai apontar para o departamento

				Seller obj = instantiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); // Excessao personalizada
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

}
