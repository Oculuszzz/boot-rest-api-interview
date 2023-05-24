/**
 * 
 */
package posmy.interview.boot.respository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import posmy.interview.boot.mock.UserFactory;
import posmy.interview.boot.model.UserEntity;

/**
 * @author mokht
 *
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private String realUserEmail;

	private UserEntity mockUser;

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

		mockUser = UserFactory.getInstance().constructUserEntityMember();
		userRepository.save(mockUser);

		realUserEmail = mockUser.getEmail();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.respository.UserRepository#existsByEmail(java.lang.String)}.
	 */
	@Test
	void testExistsByEmail() {

		assertTrue(userRepository.existsByEmail(realUserEmail));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.respository.UserRepository#findByEmail(java.lang.String)}.
	 */
	@Test
	void testFindByEmail() {

		assertThat(userRepository.findByEmail(realUserEmail)).isPresent();

	}

}
