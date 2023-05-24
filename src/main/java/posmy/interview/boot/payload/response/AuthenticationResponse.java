/**
 * 
 */
package posmy.interview.boot.payload.response;

import java.util.List;
import java.util.Objects;

/**
 * @author mokht
 *
 */
public class AuthenticationResponse {

	/**
	 * Access token.
	 */
	private String accessToken;
	/**
	 * HTTP response type - Bearer authentication/token authentication
	 */
	private String tokenType = "Bearer";

	/**
	 * User id.
	 */
	private Long id;

	/**
	 * User email.
	 */
	private String email;

	private List<String> roles;

	/**
	 * Class constructor.
	 * 
	 * @param accessToken
	 * @param id
	 * @param email
	 * @param roles
	 */
	public AuthenticationResponse(String accessToken, Long id, String email, List<String> roles) {
		this.accessToken = accessToken;
		this.id = id;
		this.email = email;
		this.roles = roles;
	}

	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, email, id, roles, tokenType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthenticationResponse other = (AuthenticationResponse) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(email, other.email)
				&& Objects.equals(id, other.id) && Objects.equals(roles, other.roles)
				&& Objects.equals(tokenType, other.tokenType);
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + ", id=" + id
				+ ", email=" + email + ", roles=" + roles + "]";
	}

}
