package posmy.interview.boot.advice;

import java.time.LocalDateTime;

/**
 * @author mokht
 *
 */
public class ErrorMessage {

	private int statusCode;
	private String message;
	private String description;
	private LocalDateTime localDateTime;

	/**
	 * @param statusCode
	 * @param message
	 * @param description
	 * @param localDateTime
	 */
	public ErrorMessage(int statusCode, String message, String description, LocalDateTime localDateTime) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.description = description;
		this.localDateTime = localDateTime;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the localDateTime
	 */
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	/**
	 * @param localDateTime the localDateTime to set
	 */
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

}
