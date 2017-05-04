package FormValidators;

public class InnovationForm {

	public long innoId;
	public String innoName;
	public String innoDesc;
	public String teamName;
	public String error;

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String innoOwner;

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

	public String getInnoOwner() {
		return innoOwner;
	}

	public void setInnoOwner(String innoOwner) {
		this.innoOwner = innoOwner;
	}

	public String getError() {
		return this.error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
