/**
 * 
 */
package posmy.interview.boot.payload.request;

import java.util.Objects;

/**
 * @author mokht
 *
 */
public class UpdateBookRequest {

	private Long id;

	private String author;

	private String bookTitle;

	private String bookDescription;

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

	@Override
	public int hashCode() {
		return Objects.hash(author, bookDescription, bookTitle, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpdateBookRequest other = (UpdateBookRequest) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookDescription, other.bookDescription)
				&& Objects.equals(bookTitle, other.bookTitle) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "UpdateBookRequest [id=" + id + ", author=" + author + ", bookTitle=" + bookTitle + ", bookDescription="
				+ bookDescription + "]";
	}

}
