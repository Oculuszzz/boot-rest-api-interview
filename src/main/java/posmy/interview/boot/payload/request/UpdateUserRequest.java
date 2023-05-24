/**
 * 
 */
package posmy.interview.boot.payload.request;

import java.util.Objects;

import posmy.interview.boot.model.ERole;

/**
 * Update user payload request class.
 * 
 * @author mokht
 *
 */
public class UpdateUserRequest {

	/**
	 * User email.
	 */
	private String email;

	/**
	 * User password.
	 */
	private String password;

	/**
	 * User role.
	 */
	private ERole role;

	/**
	 * Class constructor.
	 * 
	 * @param email
	 * @param password
	 * @param role
	 */
	public UpdateUserRequest(String email, String password, ERole role) {
		this.email = email;
		this.password = password;
		this.role = role;
	}

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

	@Override
	public int hashCode() {
		return Objects.hash(email, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateUserRequest other = (UpdateUserRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "UpdateUserRequest [email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}
