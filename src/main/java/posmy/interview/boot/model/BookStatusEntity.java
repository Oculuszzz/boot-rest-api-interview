/**
 * 
 */
package posmy.interview.boot.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Book status entity class.
 * 
 * @author mokht
 *
 */
@Entity
@Table(name = "book_status")
public class BookStatusEntity {

	/**
	 * Id of book status.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * Name of book status.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false)
	private EBookStatus status;

	/**
	 * Class constructor.
	 * 
	 */
	public BookStatusEntity() {


	}

	/**
	 * Class constructor.
	 * 
	 * @param status
	 */
	public BookStatusEntity(EBookStatus status) {
		this.status = status;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public EBookStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EBookStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookStatusEntity other = (BookStatusEntity) obj;
		return Objects.equals(id, other.id) && status == other.status;
	}

	@Override
	public String toString() {
		return "BookStatusEntity [id=" + id + ", status=" + status + "]";
	}

}
