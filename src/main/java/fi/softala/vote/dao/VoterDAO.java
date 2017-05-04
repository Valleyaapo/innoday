package fi.softala.vote.dao;

import java.util.List;

import fi.softala.vote.model.Team;
import fi.softala.vote.model.Voter;

public interface VoterDAO {
	public abstract Voter addVoter(Voter voter);

	public abstract Voter find(long id);

	public abstract Voter findByVoterName(String fname, String sname)
			throws Exception;

	public abstract void updateVoted(long id);

	public abstract void updateTeam(Voter voter, Team team);

	public abstract Voter findByVoterTeam(String fname, String sname,
			String team_name) throws Exception;

	public abstract List<Voter> findAll();
}
