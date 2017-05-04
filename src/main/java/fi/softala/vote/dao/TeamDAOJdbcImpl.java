package fi.softala.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fi.softala.vote.model.Team;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class TeamDAOJdbcImpl implements TeamDAO {

	@Inject
	private JdbcTemplate jdbc;

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbc;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	// find team by id
	public Team find(long id) {

		String query = "SELECT * FROM team WHERE team_id = ?";
		Object[] params = new Object[] { id };
		return this.jdbc.queryForObject(query, params, (result, row) -> {
			Team team = new Team();
			team.setTeamId(result.getLong("team_id"));
			team.setTeamName(result.getString("team_name"));
			return team;
		});

	}

	@Override
	// find team by name
	public Team findByTeamName(String teamName) {
		String query = "SELECT * FROM team WHERE team_name = ?";
		Object[] params = new Object[] { teamName };
		return this.jdbc.queryForObject(query, params, (result, row) -> {
			Team team = new Team();
			team.setTeamId(result.getLong("team_id"));
			team.setTeamName(result.getString("team_name"));
			return team;
		});
	}

	@Override
	// find all teams
	public List<Team> findAll() {
		String query = "SELECT team_id, team_name FROM team";
		return this.jdbc.query(query, (result, row) -> {
			Team team = new Team();
			team.setTeamId(result.getLong("team_id"));
			team.setTeamName(result.getString("team_name"));
			return team;
		});
	}

	@Override
	// add new team
	public Team addNew(Team team) {
		String SQL = "INSERT INTO team(team_name) values(?)";
		KeyHolder idHolder = new GeneratedKeyHolder();

		jdbc.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL,
						new String[] { "team_id" });
				ps.setString(1, team.getTeamName());
				return ps;
			}
		}, idHolder);

		team.setTeamId(idHolder.getKey().longValue());

		return team;
	}
}
