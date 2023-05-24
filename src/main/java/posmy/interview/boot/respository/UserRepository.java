/**
 * 
 */
package posmy.interview.boot.respository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import posmy.interview.boot.model.UserEntity;

/**
 * User repository class.
 * 
 * @author mokht
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	public Boolean existsByEmail(String email);

	public Optional<UserEntity> findByEmail(String email);

}
