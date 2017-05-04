package fi.softala.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "vote")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long voteId;

	@OneToOne
	@JoinColumn(name = "voter_id")
	private Voter voter;

	@ManyToOne
	@JoinColumn(name = "inno_id")
	private Innovation innovation;

	@org.hibernate.annotations.Type(type = "yes_no")
	@NotNull
	@Column(name = "legit")
	private boolean legit; // SQL default 'N', if vote is legit -> 'Y'

	public boolean isLegit() {
		return legit;
	}

	public long getVoteId() {
		return voteId;
	}

	public void setVoteId(long voteId) {
		this.voteId = voteId;
	}

	public Voter getVoter() {
		return voter;
	}

	public void setVoter(Voter voter) {
		this.voter = voter;
	}

	public Innovation getInnovation() {
		return innovation;
	}

	public void setInnovation(Innovation innovation) {
		this.innovation = innovation;
	}

	public void setLegit(boolean legit) {
		this.legit = legit;
	}

	@Override
	public String toString() {
		return "Vote [voteId=" + voteId + ", voter=" + voter + ", innovation="
				+ innovation + ", legit=" + legit + "]";
	}

}
