/**
 * 
 */
package posmy.interview.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import posmy.interview.boot.mapper.UserDetailsMapper;
import posmy.interview.boot.mock.UserFactory;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.respository.UserRepository;
import posmy.interview.boot.userdetails.UserDetailsImpl;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class UserDetailsServiceImplTest {

	UserDetailsServiceImpl underTest;

	@Mock
	private UserRepository userRepository;

	@Mock
	private UserDetailsMapper userDetailsMapper;

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

		underTest = new UserDetailsServiceImpl(userRepository, userDetailsMapper);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.UserDetailsServiceImpl#loadUserByUsername(java.lang.String)}.
	 */
	@Test
	void testLoadUserByUsername() {

		// given
		UserEntity mockUserEntity = UserFactory.getInstance().constructUserEntityMember();
		List<SimpleGrantedAuthority> authorities = mockUserEntity.getRole().getAuthorities();

		UserDetailsImpl expectedResult = new UserDetailsImpl(mockUserEntity.getId(), mockUserEntity.getEmail(),
				mockUserEntity.getPassword(), authorities);

		given(userRepository.findByEmail(mockUserEntity.getEmail())).willReturn(Optional.of(mockUserEntity));
		given(userDetailsMapper.mapEntityToUserDetails(mockUserEntity)).willReturn(expectedResult);

		// when
		UserDetails result = underTest.loadUserByUsername(mockUserEntity.getEmail());

		// then
		assertThat(result).isEqualTo(expectedResult);

	}

}
