/**
 * 
 */
package posmy.interview.boot.payload.request;

import java.util.Objects;

/**
 * Add Book payload request class.
 * 
 * @author mokht
 *
 */
public class AddBookRequest {

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
	 * Class constructor.
	 * 
	 * @param author
	 * @param bookTitle
	 * @param bookDescription
	 */
	public AddBookRequest(String author, String bookTitle, String bookDescription) {
		this.author = author;
		this.bookTitle = bookTitle;
		this.bookDescription = bookDescription;
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
		return Objects.hash(author, bookDescription, bookTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddBookRequest other = (AddBookRequest) obj;
		return Objects.equals(author, other.author) && Objects.equals(bookDescription, other.bookDescription)
				&& Objects.equals(bookTitle, other.bookTitle);
	}

	@Override
	public String toString() {
		return "AddBookRequest [author=" + author + ", bookTitle=" + bookTitle + ", bookDescription=" + bookDescription
				+ "]";
	}

}
