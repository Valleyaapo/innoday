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
@Table(name = "innoevent")
public class Innoevent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long eventId;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(name = "eventName", nullable = false, unique = true)
	private String eventName;

	
	public long getInnoId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String event) {
		this.eventName = eventName;
	}

	@Override
	public String toString() {
		return "Event[eventId=" + eventId + ", eventName=" + eventName + "]";
	}

}
