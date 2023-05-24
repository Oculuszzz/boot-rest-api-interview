/**
 * 
 */
package posmy.interview.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import posmy.interview.boot.mock.UserFactory;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.UserResponse;
import posmy.interview.boot.respository.UserRepository;
import posmy.interview.boot.security.JwtUtils;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class UserServiceImplTest {

	private UserServiceImpl underTest;

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@Mock
	private JwtUtils jwtUtils;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		underTest = new UserServiceImpl(userRepository, passwordEncoder, jwtUtils);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#findUsers()}.
	 */
	@Test
	void testFindUsers() {

		// given
		List<UserEntity> mockUserEntity = UserFactory.getInstance().constructUsersEntitiesMemberLibrarian();
		List<UserResponse> expectedResult = mockUserEntity.stream().map(UserResponse::new).toList();

		given(userRepository.findAll()).willReturn(mockUserEntity);

		// when
		List<UserResponse> result = underTest.findUsers();

		// then
		assertThat(result).hasSameSizeAs(expectedResult);

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#findUserById(java.lang.Long)}.
	 */
	@Test
	void testFindUserById() {

		// given
		Optional<UserEntity> mockUserEntity = Optional.of(UserFactory.getInstance().constructUserEntityMember());
		UserResponse expectedResult = mockUserEntity.map(UserResponse::new).get();

		given(userRepository.findById(1L)).willReturn(mockUserEntity);

		// when
		UserResponse result = underTest.findUserById(1L);

		// then
		assertNotNull(result);
		assertThat(result.getEmail()).isEqualTo(expectedResult.getEmail());
		assertThat(result.getId()).isEqualTo(expectedResult.getId());
		assertThat(result.getRoles()).isEqualTo(expectedResult.getRoles());

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#addUser(posmy.interview.boot.payload.request.AddUserRequest)}.
	 */
	@Test
	void testAddUser() {

		// given
		AddUserRequest mockAddUserRequest = UserFactory.getInstance().constructAddUserMemberRequest();
		UserEntity expectedResult = new UserEntity(mockAddUserRequest.getEmail(), mockAddUserRequest.getPassword(),
				mockAddUserRequest.getRole());

		given(userRepository.existsByEmail(mockAddUserRequest.getEmail())).willReturn(Boolean.FALSE);
		given(passwordEncoder.encode(mockAddUserRequest.getPassword())).willReturn(mockAddUserRequest.getPassword());

		// when
		underTest.addUser(mockAddUserRequest);

		// then
		ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		UserEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#updateUser(java.lang.Long, posmy.interview.boot.payload.request.UpdateUserRequest)}.
	 */
	@Test
	void testUpdateUser() {

		// given
		UpdateUserRequest mockUpdateUserRequest = UserFactory.getInstance().constructUpdateUserLibrianRequest();
		Optional<UserEntity> mockUserEntity = Optional.of(UserFactory.getInstance().constructUserEntityMember());
		UserEntity expectedResult = UserFactory.getInstance().constructExpectedUpdateUserEntityLibrarian();

		given(userRepository.findById(1L)).willReturn(mockUserEntity);

		// when
		underTest.updateUser(mockUserEntity.get().getId(), mockUpdateUserRequest);

		// then
		ArgumentCaptor<UserEntity> userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);
		verify(userRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		UserEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#deleteUserById(java.lang.Long)}.
	 */
	@Test
	void testDeleteUserById() {

		// given
		UserEntity mockUserEntity = UserFactory.getInstance().constructUserEntityMember();

		given(userRepository.findById(mockUserEntity.getId())).willReturn(Optional.of(mockUserEntity));

		// when
		underTest.deleteUserById(mockUserEntity.getId());

		// then
		verify(userRepository, times(1)).delete(mockUserEntity);

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserServiceImpl#deleteOwnUser(java.lang.String)}.
	 */
	@Test
	void testDeleteOwnUser() {

		// given
		UserEntity mockUserEntity = UserFactory.getInstance().constructUserEntityMember();
		String mockToken = "";

		given(jwtUtils.getUsernameFromToken(mockToken)).willReturn(mockUserEntity.getEmail());
		given(userRepository.findByEmail(mockUserEntity.getEmail())).willReturn(Optional.of(mockUserEntity));

		// when
		underTest.deleteOwnUser(mockToken);

		// then
		verify(userRepository, times(1)).delete(mockUserEntity);

	}

}
