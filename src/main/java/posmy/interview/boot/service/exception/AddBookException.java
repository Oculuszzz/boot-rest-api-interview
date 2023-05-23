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
public class AddBookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public AddBookException() {

		super();
	}

	/**
	 * 
	 */
	public AddBookException(String errorMessage) {

		super(errorMessage);

	}

}
