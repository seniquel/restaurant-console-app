package dev.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import dev.entite.Plat;

public class PlatMapper implements RowMapper<Plat> {

	@Override
	public Plat mapRow(ResultSet rs, int rowNum) throws SQLException {
		Plat plat = new Plat();
		plat.setId(rs.getInt("ID"));
		plat.setNom(rs.getString("NOM"));
		plat.setPrixEnCentimesEuros(rs.getInt("PRIX"));
		return plat;
	}

}
