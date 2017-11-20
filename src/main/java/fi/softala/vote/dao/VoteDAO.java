package fi.softala.vote.dao;

import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Vote;
import fi.softala.vote.model.Voter;

import java.util.List;

public interface VoteDAO {
	public abstract void add(Vote vote);

	public abstract List<Vote> findByInnovation(Innovation innovation);

	public abstract List<Vote> findAllVotes();
	
	public abstract List<Vote> countPercent();
	
}
