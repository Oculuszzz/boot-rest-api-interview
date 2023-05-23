/**
 * 
 */
package posmy.interview.boot.payload.response;

import java.util.ArrayList;
import java.util.List;
import posmy.interview.boot.model.UserEntity;

/**
 * @author mokht
 *
 */
public class UserResponse {

	/**
	 * User id.
	 */
	private Long id;

	/**
	 * User email.
	 */
	private String email;

	private List<String> roles = new ArrayList<>();

	/**
	 * Class constructor.
	 */
	public UserResponse() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param userEntity
	 */
	public UserResponse(UserEntity userEntity) {

		this.setId(userEntity.getId());
		this.setEmail(userEntity.getEmail());
		this.setRoles(userEntity.getRole().getAuthorities().stream().map(e -> e.getAuthority()).toList());

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

}
