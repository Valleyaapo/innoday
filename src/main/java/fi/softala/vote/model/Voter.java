package fi.softala.vote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "voter")
public class Voter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long voterId;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 225)
	private String firstName;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 225)
	private String lastName;

	@NotNull
	@NotEmpty
	@Size(min = 7, max = 7)
	@Column(name = "type")
	private String type; // SQL default; VISITOR - STUDENT, TEACHER, INNOMEM

	@NotEmpty
	@org.hibernate.annotations.Type(type = "yes_no")
	@NotNull
	@Column(name = "voted")
	private boolean voted; // SQL default 'N', if has voted -> 'Y'

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;

	public long getVoterId() {
		return voterId;
	}

	public void setVoterId(long voterId) {
		this.voterId = voterId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isVoted() {
		return voted;
	}

	public void setVoted(boolean voted) {
		this.voted = voted;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Voter [voterId=" + voterId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", type=" + type + ", voted="
				+ voted + ", team=" + team + "]";
	}

}
