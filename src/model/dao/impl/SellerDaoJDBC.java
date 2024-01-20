package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	public Seller finById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON  seller.DepartmentId = department.Id " + "WHERE seller .Id = ?");
			st.setInt(1, id);// id da linha 33
			rs = st.executeQuery();
			if (rs.next()) { // Nao tiver reultado sai do if e retorna nulo. Se tiver
				Department dep = new Department(); // variavel temporaria
				dep.setId(rs.getInt("DepartmentId")); // Nome da coluna que esta no banco
				dep.setName(rs.getString("DEpName")); // Nome do departament no banco

				// Criando o objeto seller que vai apontar para o departamento

				Seller obj = new Seller();
				obj.setId(rs.getInt("Id")); // nome da coluna que esta no banco
				obj.setName(rs.getString("Name")); // nome da coluna esta no banco
				obj.setEmail(rs.getString("Email"));// nome da coluna esta no banco
				obj.setBaseSalary(rs.getDouble("BaseSalary"));
				obj.setBirthDate(rs.getDate("BirthDate"));
				obj.setDepartment(dep); // Associacao de objetos montado
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage()); // Excessao personalizada
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
