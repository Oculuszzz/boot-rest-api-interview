/**
 * 
 */
package posmy.interview.boot.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import posmy.interview.boot.mapper.UserDetailsMapper;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.respository.UserRepository;

/**
 * Implementation of user details service.
 * 
 * @author mokht
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * The user repository object.
	 */
	private final UserRepository userRepository;

	private final UserDetailsMapper userDetailsMapper;

	/**
	 * Class constructor.
	 * 
	 * @param userRepository
	 * @param userDetailsMapper
	 */
	public UserDetailsServiceImpl(UserRepository userRepository, UserDetailsMapper userDetailsMapper) {

		this.userRepository = userRepository;
		this.userDetailsMapper = userDetailsMapper;

	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format("User is not found %s!", username)));

		return userDetailsMapper.mapEntityToUserDetails(userEntity);

	}

}
