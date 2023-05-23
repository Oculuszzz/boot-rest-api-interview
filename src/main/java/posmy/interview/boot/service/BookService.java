/**
 * 
 */
package posmy.interview.boot.service;

import java.util.List;

import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;
import posmy.interview.boot.payload.response.BookResponse;

/**
 * Book service interface class.
 * 
 * @author mokht
 *
 */
public interface BookService {

	/**
	 * Responsible to find all books.
	 * 
	 * @return
	 */
	public List<BookResponse> findBooks();

	/**
	 * Responsible to find book by book id.
	 * 
	 * @param bookId
	 * @return
	 */
	public BookResponse findBookById(Long bookId);

	/**
	 * Responsible to create a new book.
	 * 
	 * @param addBookRequest
	 */
	public void addBook(AddBookRequest addBookRequest);

	/**
	 * Responsible to update an existing book.
	 * 
	 * @param updateBookRequest
	 */
	public void updateBook(UpdateBookRequest updateBookRequest);

	/**
	 * Responsible to delete/remove an existing book.
	 * 
	 * @param bookId
	 */
	public void deleteBookById(Long bookId);

	/**
	 * Responsible to update book status (BORROWED).
	 * 
	 * @param headerAuth
	 * @param bookId
	 */
	public void borrowBookById(String headerAuth, Long bookId);

	/**
	 * Responsible to update book status (AVAILABLE).
	 * 
	 * @param headerAuth
	 * @param bookId
	 */
	public void returnBookById(Long bookId);

}
