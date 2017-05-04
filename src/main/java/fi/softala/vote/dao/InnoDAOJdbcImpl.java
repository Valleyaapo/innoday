package fi.softala.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import fi.softala.vote.model.Innovation;

@Repository
public class InnoDAOJdbcImpl implements InnovationDAO {

	@Inject
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// add new innovation
	public Innovation addNew(Innovation inno) {
		final String sql = "insert into inno(team_id, inno_name, inno_desc, inno_owner) values(?,?,?, ?)";

		final long innoTeamID = inno.getTeam().getTeamId();
		final String innoName = inno.getInnoName();
		final String innoDesc = inno.getInnoDesc();
		final String innoOwner = inno.getInnoOwner();

		KeyHolder idHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql,
						new String[] { "inno_id" });
				ps.setLong(1, innoTeamID);
				ps.setString(2, innoName);
				ps.setString(3, innoDesc);
				ps.setString(4, innoOwner);
				return ps;
			}
		}, idHolder);

		inno.setInnoId(idHolder.getKey().intValue());

		return inno;
	}

	// find by innovation's id
	public Innovation find(long id) {
		String sql = "select inno_name, inno_desc, inno_id, inno.team_id, inno_owner, team_name from inno join team using(team_id) where inno_id = ?";
		Object[] parameters = new Object[] { id };
		RowMapper<Innovation> mapper = new InnoRowMapper();

		Innovation inno;
		try {
			inno = jdbcTemplate.queryForObject(sql, parameters, mapper);
		} catch (IncorrectResultSizeDataAccessException e) {
			throw new InnoNotFoundException(e);
		}
		return inno;
	}

	// find all innovations and their teams
	public List<Innovation> findAll() {

		String sql = "select inno_id, inno_name, inno_desc, team_id, inno_owner, team_name from inno join team using(team_id)";
		RowMapper<Innovation> mapper = new InnoRowMapper();
		List<Innovation> innovations = jdbcTemplate.query(sql, mapper);

		return innovations;
	}

	// find all innovations by a team
	public List<Innovation> findByTeamId(long teamId) {
		String sql = "select * from inno where team_id = ?";
		Object[] params = new Object[] { teamId };
		RowMapper<Innovation> mapper = new InnoRowMapper();
		List<Innovation> innovations = jdbcTemplate.query(sql, params, mapper);
		return innovations;
	}
}