/**
 * 
 */
package posmy.interview.boot.mapper;

import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.userdetails.UserDetailsImpl;

/**
 * @author mokht
 *
 */
@Component
public class UserDetailsMapper {

	public UserDetailsImpl mapEntityToUserDetails(UserEntity userEntity) {

		List<SimpleGrantedAuthority> authorities = userEntity.getRole().getAuthorities();

		return new UserDetailsImpl(userEntity.getId(), userEntity.getEmail(), userEntity.getPassword(), authorities);

	}

}
