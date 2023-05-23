/**
 * 
 */
package posmy.interview.boot.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import posmy.interview.boot.model.BookEntity;

/**
 * Book repository class.
 * 
 * @author mokht
 *
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

	public Optional<BookEntity> findById(Long id);

	public List<BookEntity> findAll();

}
