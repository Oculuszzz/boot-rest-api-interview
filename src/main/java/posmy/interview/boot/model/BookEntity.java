/**
 * 
 */
package posmy.interview.boot.model;

import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Book entity class.
 * 
 * @author mokht
 *
 */
@Entity
@Table(name = "books")
public class BookEntity {

	/**
	 * Id of book.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * Book author.
	 */
	@Column(name = "author", nullable = false)
	private String author;

	/**
	 * Book title.
	 */
	@Column(name = "book_title", nullable = false, unique = true)
	private String bookTitle;

	/**
	 * Book description.
	 */
	@Column(name = "book_description")
	private String bookDescription;

	/**
	 * User role.
	 */
	@Enumerated(EnumType.STRING)
	private EBookStatus status;

	/**
	 * Book borrowed by user.
	 */
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "book_borrowed_by_user", joinColumns = @JoinColumn(name = "book_id", nullable = true), inverseJoinColumns = @JoinColumn(name = "user_id", nullable = true))
	private UserEntity borrowedByUser;

	/**
	 * Class constructor.
	 */
	public BookEntity() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param id
	 * @param author
	 * @param bookTitle
	 * @param bookDescription
	 * @param status
	 * @param borrowedByUser
	 */
	public BookEntity(Long id, String author, String bookTitle, String bookDescription, EBookStatus status,
			UserEntity borrowedByUser) {
		this.id = id;
		this.author = author;
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
		this.status = status;
		this.borrowedByUser = borrowedByUser;
	}

	/**
	 * Class constructor.
	 * 
	 * @param author
	 * @param bookTitle
	 * @param bookDescription
	 * @param status
	 */
	public BookEntity(String author, String bookTitle, String bookDescription, EBookStatus status) {
		this.author = author;
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
		this.status = status;
	}

	/**
	 * Class constructor.
	 * 
	 * @param author
	 * @param bookTitle
	 * @param bookDescription
	 * @param status
	 * @param borrowedByUser
	 */
	public BookEntity(String author, String bookTitle, String bookDescription, EBookStatus status,
			UserEntity borrowedByUser) {
		this.author = author;
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
		this.status = status;
		this.borrowedByUser = borrowedByUser;
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
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the bookTitle
	 */
	public String getBookTitle() {
		return bookTitle;
	}

	/**
	 * @param bookTitle the bookTitle to set
	 */
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	/**
	 * @return the bookDescription
	 */
	public String getBookDescription() {
		return bookDescription;
	}

	/**
	 * @param bookDescription the bookDescription to set
	 */
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
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

	/**
	 * @return the borrowedByUser
	 */
	public UserEntity getBorrowedByUser() {
		return borrowedByUser;
	}

	/**
	 * @param borrowedByUser the borrowedByUser to set
	 */
	public void setBorrowedByUser(UserEntity borrowedByUser) {
		this.borrowedByUser = borrowedByUser;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, bookDescription, bookTitle, borrowedByUser, id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookEntity other = (BookEntity) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookDescription, other.bookDescription)
				&& Objects.equals(bookTitle, other.bookTitle) && Objects.equals(borrowedByUser, other.borrowedByUser)
				&& Objects.equals(id, other.id) && status == other.status;
	}

	@Override
	public String toString() {
		return "BookEntity [id=" + id + ", author=" + author + ", bookTitle=" + bookTitle + ", bookDescription="
				+ bookDescription + ", status=" + status + ", borrowedByUser=" + borrowedByUser + "]";
	}

}
