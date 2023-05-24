/**
 * 
 */
package posmy.interview.boot.payload.response;

import java.util.Objects;

import posmy.interview.boot.model.BookEntity;
import posmy.interview.boot.model.EBookStatus;

/**
 * @author mokht
 *
 */
public class BookResponse {

	/**
	 * Book id.
	 */
	private Long id;

	/**
	 * Book author.
	 */
	private String author;

	/**
	 * Book title.
	 */
	private String bookTitle;

	/**
	 * Book description.
	 */
	private String bookDescription;

	/**
	 * Book status.
	 */
	private EBookStatus status;

	/**
	 * Book borrow by user (email).
	 */
	private String borrowByUserEmail;

	/**
	 * Class constructor.
	 */
	public BookResponse() {

	}

	/**
	 * Class constructor.
	 * 
	 * @param bookEntity
	 */
	public BookResponse(BookEntity bookEntity) {

		this.setId(bookEntity.getId());
		this.setAuthor(bookEntity.getAuthor());
		this.setBookTitle(bookEntity.getBookTitle());
		this.setBookDescription(bookEntity.getBookDescription());
		this.setStatus(bookEntity.getStatus());
		this.setBorrowByUserEmail(
				bookEntity.getBorrowedByUser() != null ? bookEntity.getBorrowedByUser().getEmail() : "");

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
	 * @return the borrowByUserEmail
	 */
	public String getBorrowByUserEmail() {
		return borrowByUserEmail;
	}

	/**
	 * @param borrowByUserEmail the borrowByUserEmail to set
	 */
	public void setBorrowByUserEmail(String borrowByUserEmail) {
		this.borrowByUserEmail = borrowByUserEmail;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, bookDescription, bookTitle, borrowByUserEmail, id, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookResponse other = (BookResponse) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookDescription, other.bookDescription)
				&& Objects.equals(bookTitle, other.bookTitle)
				&& Objects.equals(borrowByUserEmail, other.borrowByUserEmail) && Objects.equals(id, other.id)
				&& status == other.status;
	}

	@Override
	public String toString() {
		return "BookResponse [id=" + id + ", author=" + author + ", bookTitle=" + bookTitle + ", bookDescription="
				+ bookDescription + ", status=" + status + ", borrowByUserEmail=" + borrowByUserEmail + "]";
	}

}
