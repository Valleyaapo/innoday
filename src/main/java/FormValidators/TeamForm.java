package FormValidators;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeamForm {

	@NotNull
	@NotEmpty
	@Size(min = 3, max = 25)
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

}
