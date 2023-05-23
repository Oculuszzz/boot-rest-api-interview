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
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UserException() {

		super();
	}

	/**
	 * 
	 */
	public UserException(String errorMessage) {

		super(errorMessage);

	}

}
