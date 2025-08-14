package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbExeption;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class SellerDaoImplementsJdbc implements SellerDao {

	private Connection conn;

	public SellerDaoImplementsJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
		PreparedStatement st = null;
		
		try{
		
			st = conn.prepareStatement("INSERT INTO seller\r\n"
					+ "	(Name, Email, BirthDate, BaseSalary, DepartmentId)\r\n"
					+ "	VALUES\r\n"
					+ "	(?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS );
			
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartament().getId());
			
			int linhasAlteradas = st.executeUpdate();
			if(linhasAlteradas > 0 ) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				}
			}else {
				throw new DbExeption("Error inesperado, nenhuma lina encotrada!!!");
			}
			
		}catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		}finally {
			DB.closeStatement(st);
			
		}
		


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
			st = conn.prepareStatement("" + "SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n" + "ON seller.DepartmentId = department.Id\r\n"
					+ "WHERE DepartmentId = ?\r\n" + "ORDER BY Name");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {

				Departament dep = instantiateDepartment(rs);
				Seller obj = insatntiateSeller(rs, dep);

				return obj;
			}
			return null;
		} catch (Exception e) {
			throw new DbExeption(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}

	}

	private Seller insatntiateSeller(ResultSet rs, Departament dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartament(dep);

		return obj;
	}

	private Departament instantiateDepartment(ResultSet rs) throws SQLException {
		Departament dep = new Departament();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));

		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement( ""
					+ "SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					//linha where removida
					+ "ORDER BY Name");

			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer , Departament> map = new HashMap<>();
			
			
			while (rs.next()) {
				
				Departament dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
				dep = instantiateDepartment(rs);
				map.put(rs.getInt("DepartmentId"), dep);
				
				}
				Seller obj = insatntiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			throw new DbExeption(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findByDepartament(Departament departament) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement( ""
					+ "SELECT seller.*,department.Name as DepName\r\n"
					+ "FROM seller INNER JOIN department\r\n"
					+ "ON seller.DepartmentId = department.Id\r\n"
					+ "WHERE DepartmentId = ?\r\n"
					+ "ORDER BY Name");

			st.setInt(1, departament.getId());
			rs = st.executeQuery();
			
			List<Seller> list = new ArrayList<>();
			Map<Integer , Departament> map = new HashMap<>();
			
			
			while (rs.next()) {
				
				Departament dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
				dep = instantiateDepartment(rs);
				map.put(rs.getInt("DepartmentId"), dep);
				
				}
				Seller obj = insatntiateSeller(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			throw new DbExeption(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}
	

}
