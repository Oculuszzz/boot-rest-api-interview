/**
 * 
 */
package posmy.interview.boot.service;

import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.UserResponse;
import posmy.interview.boot.respository.UserRepository;
import posmy.interview.boot.security.JwtUtils;
import posmy.interview.boot.service.exception.AddUserException;
import posmy.interview.boot.service.exception.UpdateUserException;
import posmy.interview.boot.service.exception.UserException;

/**
 * Implementation of user service class.
 * 
 * @author mokht
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtUtils jwtUtils;

	/**
	 * Class constructor.
	 * 
	 * @param userRepository
	 * @param passwordEncoder
	 * @param jwtUtils
	 */
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.jwtUtils = jwtUtils;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserResponse> findUsers() {

		List<UserEntity> userEntities = userRepository.findAll();

		return userEntities.stream().map(UserResponse::new).toList();

	}

	@Override
	@Transactional(readOnly = true)
	public UserResponse findUserById(Long userId) {

		return userRepository.findById(userId).map(UserResponse::new)
				.orElseThrow(() -> new UserException("Invalid user!"));
	}

	@Override
	@Transactional
	public void addUser(AddUserRequest request) {

		// Validate existing email
		if (Boolean.TRUE.equals(userRepository.existsByEmail(request.getEmail()))) {

			throw new AddUserException("Please use a different email to continue!");

		}

		UserEntity userEntity = new UserEntity(request.getEmail(), passwordEncoder.encode(request.getPassword()),
				request.getRole());

		userRepository.save(userEntity);

	}

	@Override
	@Transactional
	public void updateUser(Long id, UpdateUserRequest request) {

		// Find user
		UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new UpdateUserException("Invalid user!"));

		userEntity.setEmail(request.getEmail());
		userEntity.setRole(request.getRole());

		userRepository.save(userEntity);

	}

	@Override
	@Transactional
	public void deleteUserById(Long userId) {

		userRepository.deleteById(userId);

	}

	@Override
	@Transactional
	public void deleteOwnUser(String headerAuth) {

		String token = "";

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer-")) {

			token = headerAuth.substring(7, headerAuth.length());

		}

		// Find user
		UserEntity userEntity = userRepository.findByEmail(jwtUtils.getUsernameFromToken(token))
				.orElseThrow(() -> new UserException("Invalid user!"));

		userRepository.deleteById(userEntity.getId());

	}

}
