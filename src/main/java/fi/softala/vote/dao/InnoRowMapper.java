package fi.softala.vote.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Team;

public class InnoRowMapper implements RowMapper<Innovation> {
	public Innovation mapRow(ResultSet rs, int rowNum) throws SQLException {
		Innovation inno = new Innovation();
		inno.setInnoId(rs.getLong("inno_id"));
		inno.setInnoName(rs.getString("inno_name"));
		inno.setInnoDesc(rs.getString("inno_desc"));
		inno.setInnoOwner(rs.getString("inno_owner"));

		try { // inject team into innovation
			Team team = new Team();
			team.setTeamId(rs.getLong("team_id"));
			team.setTeamName(rs.getString("team_name"));
			inno.setTeam(team);
			return inno;
		} catch (Exception e) {
			inno.setTeam(null);
			return inno;
		}
	}
}
