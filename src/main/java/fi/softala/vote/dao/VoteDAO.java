package fi.softala.vote.dao;

import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Vote;
import java.util.List;

public interface VoteDAO {
	public abstract void add(Vote vote);

	public abstract List<Vote> findByInnovation(Innovation innovation);

	public abstract List<Vote> findAllVotes();
}
