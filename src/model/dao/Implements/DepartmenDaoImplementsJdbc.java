package model.dao.Implements;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbExeption;
import model.dao.DepartamentoDao;
import model.entities.Departament;
import model.entities.Seller;

public class DepartmenDaoImplementsJdbc implements DepartamentoDao {

	// fazendo a conexão
	private Connection conn;

	public DepartmenDaoImplementsJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Departament obj) {

		PreparedStatement st = null;

		try {

			st = conn.prepareStatement("INSERT INTO Department" + "(Name)" + "VALUES" + "( ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());

			int linhasAlteradas = st.executeUpdate();
			if (linhasAlteradas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
					DB.closeResultSet(rs);
				} else {
					throw new DbExeption("Error a o inserir um novo departamento");
				}

			}

		} catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}

	}

	@Override
	public void update(Departament obj) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE Department SET NAME = ? WHERE Id = ?", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getName());
			st.setInt(2, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
		
		

	}
	
	public Departament findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Departament obj = new Departament();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		
		try {
		st = conn.prepareStatement("Delete From department WHERE id = ?");
		
		st.setInt(1, id);
		
		st.executeUpdate();
		
		}catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			
		}
	}
	

	

	@Override
	public List<Departament> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT * FROM department ORDER BY Name");
			rs = st.executeQuery();

			List<Departament> list = new ArrayList<>();

			while (rs.next()) {
				Departament obj = new Departament();
				obj.setId(rs.getInt("Id"));
				obj.setName(rs.getString("Name"));
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbExeption(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
	}


