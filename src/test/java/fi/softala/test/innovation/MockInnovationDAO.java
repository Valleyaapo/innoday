package fi.softala.test.innovation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.mockito.InjectMocks;

import fi.softala.vote.dao.InnovationDAO;
import fi.softala.vote.model.*;

public class MockInnovationDAO implements InnovationDAO {
	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public Innovation addNew(Innovation inno) {
//		final String sql = "insert into inno(team_id, inno_name, inno_desc) values(?,?,?)";
//
//		long teamID = 1;
//		String innoNimi = "uusi nimi";
//		String innoKuvaus = "uusi kuvaus";
//
//		KeyHolder idHolder = new GeneratedKeyHolder();
//
//		jdbcTemplate.update(new PreparedStatementCreator() {
//			public PreparedStatement createPreparedStatement(
//					Connection connection) throws SQLException {
//				PreparedStatement ps = connection.prepareStatement(sql,
//						new String[] { "id" });
//				ps.setLong(1, inno.getTeamId());
//				ps.setString(2, inno.getInnoName());
//				ps.setString(3, inno.getInnoDesc());
//				return ps;
//			}
//		}, idHolder);
//
//		inno.setInnoId(idHolder.getKey().longValue());
		return inno;
	}
	
	public Innovation find(long id) {
		return new Innovation();
	}
	
	public List<Innovation>	findAll(){
		List <Innovation> innovations = new ArrayList<Innovation>();
		Innovation inno = new Innovation();
//		inno.set=1;
//		innovation.name="Virtual glasses";
//		innovation.topic="Virtuality";
//		innovations.add(innovation);
		return innovations;
	}
}