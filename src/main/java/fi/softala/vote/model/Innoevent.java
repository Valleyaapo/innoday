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
	@Column(name = "event_id")
	private long eventId;

	@NotNull
	@NotEmpty
	@Size(min = 1, max = 50)
	@Column(name = "eventName", nullable = false, unique = true)
	private String eventName;
	
	@NotNull
	@NotEmpty
	@Size(min = 4, max = 4)
	@Column(name = "eventYear", nullable = false, unique = true)
	private int eventYear;
	
	@NotNull
	@NotEmpty
	@Size(min = 6, max = 6)
	@Column(name = "eventSemester", nullable = false, unique = true)
	private String eventSemester;
	// spring or autumn - no other options

	
	public long getEventId() {
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
