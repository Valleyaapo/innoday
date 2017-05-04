package fi.softala.vote.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InnoNotFoundException extends RuntimeException {

	public InnoNotFoundException(Exception cause) {
		super(cause);
	}

}