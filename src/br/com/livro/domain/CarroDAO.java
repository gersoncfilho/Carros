package br.com.livro.domain;

import br.com.livro.domain.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import  java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO extends BaseDAO {
	public Carro getCarroById(Long id) throws SQLException
	{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			conn = getConnection();
			stmt = conn.prepareStatement("select * from carro where id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Carro c = createCarro(rs);
				rs.close();
				return c;
			}
		}finally
		{
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
		}
		return null;
		
	}
	
}
