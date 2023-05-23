/**
 * 
 */
package posmy.interview.boot.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mokht
 *
 */
@ResponseStatus(HttpStatus.NOT_MODIFIED)
public class UpdateUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UpdateUserException() {

		super();
	}

	/**
	 * 
	 */
	public UpdateUserException(String errorMessage) {

		super(errorMessage);

	}

}