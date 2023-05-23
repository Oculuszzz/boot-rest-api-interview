/**
 * 
 */
package posmy.interview.boot.model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static posmy.interview.boot.model.EPermission.*;

/**
 * Role enum class.
 * 
 * @author mokht
 *
 */
public enum ERole {

	LIBRARIAN(
	          Set.of(
	        		  LIBRARIAN_READ,
	        		  LIBRARIAN_UPDATE,
	        		  LIBRARIAN_CREATE,
	        		  LIBRARIAN_DELETE,
	        		  MEMBER_READ,
	        		  MEMBER_UPDATE,
	        		  MEMBER_CREATE,
	        		  MEMBER_DELETE
	          )
	          ),
	MEMBER(
	          Set.of(
	        		  MEMBER_READ,
	        		  MEMBER_UPDATE,
	        		  MEMBER_CREATE,
	        		  MEMBER_DELETE
	          )
	);

	/**
	 * 
	 */
	private final Set<EPermission> permissions;

	/**
	 * @param permission
	 */
	private ERole(Set<EPermission> permissions) {
		this.permissions = permissions;
	}

	/**
	 * @return the permissions
	 */
	public Set<EPermission> getPermissions() {
		return permissions;
	}

	public List<SimpleGrantedAuthority> getAuthorities() {
	    var authorities = getPermissions()
	            .stream()
	            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	            .collect(Collectors.toList());
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	    return authorities;
	}

}
