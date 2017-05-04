package fi.softala.vote.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "team")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long teamId;

	@NotNull
	@NotEmpty()
	@Size(min = 1, max = 50)
	@Column(name = "team_name", nullable = false, columnDefinition = "String default 'not_in_team'")
	private String teamName; // team_id and team_name default setup =
								// columnDefinition...

	@Column(name = "team_id", columnDefinition = "Bigint default '1'")
	// 1 = not_in_team
	public long getTeamId() {
		return teamId;
	}

	public void setTeamId(long teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj != this)
			return false;
		if (!(obj instanceof Team))
			return false;
		Team team = (Team) obj;
		return this.teamId == team.getTeamId()
				&& this.getTeamName() == team.getTeamName();
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.teamId, this.teamName);
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + "]";
	}

}
