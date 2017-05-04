package fi.softala.vote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import fi.softala.vote.model.Team;
import fi.softala.vote.model.Voter;

import javax.inject.Inject;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class VoterDAOJdbcImpl implements VoterDAO {

	@Inject
	private JdbcTemplate jdbc;

	@Inject
	private TeamDAOJdbcImpl teamdao;

	public JdbcTemplate getJdbcTemplate() {
		return this.jdbc;
	}

	public void setJdbcTemplate(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	// find voter by name
	public Voter findByVoterName(String fname, String sname) throws Exception {
		String query = "SELECT * FROM voter WHERE fname = ? AND sname = ? AND type != 'INNOMEM' AND voted = 'N' ORDER BY voter_id ASC Limit 1";
		Object[] params = new Object[] { fname, sname };

		return jdbc.queryForObject(query, params, (result, row) -> {

			Voter voter = new Voter();
			voter.setFirstName(result.getString("fname"));
			voter.setLastName(result.getString("sname"));
			voter.setTeam(teamdao.find(result.getLong("team_id")));
			voter.setType(result.getString("type"));
			voter.setVoted(result.getBoolean("voted"));
			voter.setVoterId(result.getLong("voter_id"));
			System.out.println(voter);
			return voter;
		});

	}

	@Override
	// find voter by team
	public Voter findByVoterTeam(String fname, String sname, String team_name)
			throws Exception {
		String query = "SELECT voter_id, fname, sname, type, voted, team_id FROM voter JOIN team USING(team_id)  WHERE fname = ? AND sname = ? AND team_name = ? AND voted = 'N' ORDER BY voter_id ASC Limit 1;";
		Object[] params = new Object[] { fname, sname, team_name };

		return jdbc.queryForObject(query, params, (result, row) -> {
			Voter voter = new Voter();
			voter.setVoterId(result.getLong("voter_id"));
			voter.setFirstName(result.getString("fname"));
			voter.setLastName(result.getString("sname"));
			voter.setType(result.getString("type"));
			voter.setVoted(result.getBoolean("voted"));
			voter.setTeam(teamdao.find(result.getLong("team_id")));

			System.out.println(voter);
			return voter;
		});
	}

	@Override
	// add new voter
	public Voter addVoter(Voter voter) {
		String SQL = "insert into voter(fname, sname, type, team_id) values(?, ?, ?, ?)";
		final String FNAME = voter.getFirstName();
		final String SNAME = voter.getLastName();
		final String TYPE = voter.getType();
		final long TEAMID = voter.getTeam().getTeamId();

		KeyHolder idHolder = new GeneratedKeyHolder();

		jdbc.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(
					Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(SQL,
						new String[] { "voter_id" });
				ps.setString(1, FNAME);
				ps.setString(2, SNAME);
				ps.setString(3, TYPE);
				ps.setLong(4, TEAMID);
				return ps;
			}
		}, idHolder);

		voter.setVoterId(idHolder.getKey().longValue());

		return voter;
	}

	@Override
	// find voter by id
	public Voter find(long id) {
		String query = " SELECT fname, sname, type, team_name, voted FROM voter JOIN team USING(team_id) WHERE voter_id = ?";
		Object[] params = new Object[] { id };

		return (Voter) this.jdbc.queryForObject(query, params,
				(result, row) -> {
					Voter voter = new Voter();
					voter.setFirstName(result.getString("fname"));
					voter.setLastName(result.getString("sname"));
					voter.setType(result.getString("type"));
					voter.setTeam(teamdao.findByTeamName(result
							.getString("team_name")));
					String votedString = result.getString("voted");
					if (votedString.equalsIgnoreCase("Y")) {
						voter.setVoted(true);
					} else if (votedString.equalsIgnoreCase("N")) {
						voter.setVoted(false);
					}
					return voter;
				});

	}

	@Override
	// find all voters
	public List<Voter> findAll() {
		String query = "SELECT * FROM voter";
		return this.jdbc.query(query, (result, row) -> {
			Voter voter = new Voter();
			voter.setVoterId(result.getLong("voter_id"));
			voter.setFirstName(result.getString("fname"));
			voter.setLastName(result.getString("sname"));
			voter.setType(result.getString("type"));
			voter.setVoted(result.getBoolean("voted"));
			Team team = new Team();
			team.setTeamId(result.getLong("team_id"));
			team = teamdao.find(result.getLong("team_id"));
			voter.setTeam(team);
			return voter;
		});
	}

	@Override
	// update voter's voted-status
	public void updateVoted(long id) {
		final String SQL = "UPDATE voter SET voted='Y' WHERE voter_id=?";
		Object[] params = new Object[] { id };
		jdbc.update(SQL, params);
	}

	@Override
	// update a voter's team_id
	public void updateTeam(Voter voter, Team team) {
		final String SQL = "UPDATE voter SET team_id=? WHERE voter_id=?";
		Object[] params = new Object[] { team.getTeamId(), voter.getVoterId() };
		jdbc.update(SQL, params);
	}

	// find voters (members) of a team
	public List<Voter> findByTeamId(long teamId) {
		final String SQL = "SELECT * FROM voter WHERE team_id = ?";
		Object[] params = new Object[] { teamId };
		return jdbc.query(SQL, params, (result, row) -> {
			Voter voter = new Voter();
			voter.setVoterId(result.getLong("voter_id"));
			voter.setFirstName(result.getString("fname"));
			voter.setLastName(result.getString("sname"));
			voter.setType(result.getString("type"));
			voter.setVoted(result.getBoolean("voted"));
			Team team = new Team();
			team.setTeamId(result.getLong("team_id"));
			team = teamdao.find(result.getLong("team_id"));
			voter.setTeam(team);
			return voter;
		});
	}
}
