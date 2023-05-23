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
public class UpdateBookException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UpdateBookException() {

		super();
	}

	/**
	 * 
	 */
	public UpdateBookException(String errorMessage) {

		super(errorMessage);

	}

}
