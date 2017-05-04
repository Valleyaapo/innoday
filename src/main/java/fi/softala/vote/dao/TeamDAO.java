package fi.softala.vote.dao;

import java.util.List;

import fi.softala.vote.model.Team;

public interface TeamDAO {
	public abstract Team find(long id);

	public abstract Team findByTeamName(String teamName);

	public abstract List<Team> findAll();

	public abstract Team addNew(Team team);
}
