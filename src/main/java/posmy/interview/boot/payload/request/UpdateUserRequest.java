/**
 * 
 */
package posmy.interview.boot.payload.request;

import posmy.interview.boot.model.ERole;

/**
 * @author mokht
 *
 */
public class UpdateUserRequest {

	private String email;

	private String password;

	private ERole role;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public ERole getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(ERole role) {
		this.role = role;
	}

}
