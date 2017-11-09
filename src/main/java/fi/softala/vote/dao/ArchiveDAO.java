package fi.softala.vote.dao;

import java.util.List;

import fi.softala.vote.model.Innoevent;
import fi.softala.vote.model.Innovation;
import fi.softala.vote.model.Team;
import fi.softala.vote.model.Vote;
import fi.softala.vote.model.Voter;

public interface ArchiveDAO{
	
	public abstract Innoevent addEvent(Innoevent event);
	
	public abstract Innoevent find(long event_id);
	
	public abstract List<Innoevent> findAll();
	
	public abstract void insertArchive(Innovation inno, Vote vote, Team team, Innoevent event);
	
	public abstract void deleteUnused(Innovation inno, Voter voter, Team team, Vote vote);
}