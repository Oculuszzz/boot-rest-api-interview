/**
 * 
 */
package posmy.interview.boot.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import posmy.interview.boot.model.BookEntity;

/**
 * Book repository class.
 * 
 * @author mokht
 *
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}
