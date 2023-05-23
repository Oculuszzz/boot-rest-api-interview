/**
 * 
 */
package posmy.interview.boot.userdetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author mokht
 *
 */
public class UserDetailsImpl implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * User Id
	 */
	private Long id;

	/**
	 * User email
	 */
	private String email;

	/**
	 * User password
	 */
	@JsonIgnore
	private String password;

	/**
	 * USer roles
	 */
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @param id
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserDetailsImpl(Long id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

}
