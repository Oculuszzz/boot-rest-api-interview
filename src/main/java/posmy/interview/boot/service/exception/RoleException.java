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
public class RoleException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public RoleException() {

		super();
	}

	/**
	 * 
	 */
	public RoleException(String errorMessage) {

		super(errorMessage);

	}

}
