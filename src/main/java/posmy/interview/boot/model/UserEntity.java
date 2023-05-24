/**
 * 
 */
package posmy.interview.boot.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @author mokht
 *
 */
@Entity
@Table(name = "users")
public class UserEntity {

	/**
	 * User id.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * User email.
	 */
	@Column(name = "email", unique = true)
	private String email;

	/**
	 * User password.
	 */
	@Column(name = "password")
	private String password;

	/**
	 * User role.
	 */
	@Enumerated(EnumType.STRING)
	private ERole role;

	/**
	 * Class constructor.
	 */
	public UserEntity() {

	}

	/**
	 * @param id
	 * @param email
	 * @param password
	 * @param role
	 */
	public UserEntity(Long id, String email, String password, ERole role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	/**
	 * Class constructor.
	 * 
	 * @param email
	 * @param password
	 * @param role
	 */
	public UserEntity(String email, String password, ERole role) {
		this.email = email;
		this.password = password;
		this.role = role;
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
		return Objects.hash(email, id, password, role);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && role == other.role;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", email=" + email + ", password=" + password + ", role=" + role + "]";
	}

}
