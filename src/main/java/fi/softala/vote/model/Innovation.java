package fi.softala.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "inno")
public class Innovation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long innoId;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(name = "inno_name", nullable = false, unique = true)
	private String innoName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 225)
	@Column(name = "inno_desc", nullable = false)
	private String innoDesc;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	@Size(min = 1, max = 100)
	private String innoOwner;

	private long voteCount; // votes existing for this innovation

	public long getInnoId() {
		return innoId;
	}

	public void setInnoId(long innoId) {
		this.innoId = innoId;
	}

	public String getInnoName() {
		return innoName;
	}

	public void setInnoName(String innoName) {
		this.innoName = innoName;
	}

	public String getInnoDesc() {
		return innoDesc;
	}

	public void setInnoDesc(String innoDesc) {
		this.innoDesc = innoDesc;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getInnoOwner() {
		return innoOwner;
	}

	public void setInnoOwner(String innoOwner) {
		this.innoOwner = innoOwner;
	}

	public long getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(long voteCount) {
		this.voteCount = voteCount;
	}

	@Override
	public String toString() {
		return "Inno[innoId=" + innoId + ", innoName=" + innoName
				+ ", innoDesc=" + innoDesc + ", team=" + team + ", innoOwner="
				+ innoOwner + "]";
	}

}
