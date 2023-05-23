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
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AddUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AddUserException() {

		super();
	}

	/**
	 * 
	 */
	public AddUserException(String errorMessage) {

		super(errorMessage);

	}

}
