/**
 * 
 */
package posmy.interview.boot.payload.response;

import java.util.Objects;

/**
 * @author mokht
 *
 */
public class MessageResponse {

	/**
	 * Message response.
	 */
	private String message;

	/**
	 * @param message
	 */
	public MessageResponse(String message) {
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(message);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageResponse other = (MessageResponse) obj;
		return Objects.equals(message, other.message);
	}

	@Override
	public String toString() {
		return "MessageResponse [message=" + message + "]";
	}

}
