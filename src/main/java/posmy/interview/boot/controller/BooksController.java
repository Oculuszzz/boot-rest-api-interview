/**
 * 
 */
package posmy.interview.boot.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;
import posmy.interview.boot.payload.response.BookResponse;
import posmy.interview.boot.payload.response.MessageResponse;
import posmy.interview.boot.prepost.PermissionLibrarianAndMemberToRead;
import posmy.interview.boot.prepost.PermissionLibrarianAndMemberToUpdate;
import posmy.interview.boot.prepost.PermissionLibrarianToCreate;
import posmy.interview.boot.prepost.PermissionLibrarianToDelete;
import posmy.interview.boot.prepost.PermissionLibrarianToUpdate;
import posmy.interview.boot.service.BookServiceImpl;

/**
 * Book controller class.
 * 
 * @author mokht
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/books")
@PreAuthorize("hasAnyRole('LIBRARIAN', 'MEMBER')")
public class BooksController {

	/**
	 * Book service object.
	 */
	private final BookServiceImpl bookService;

	/**
	 * Class constructor.
	 * 
	 * @param bookService
	 */
	public BooksController(BookServiceImpl bookService) {

		this.bookService = bookService;

	}

	/**
	 * Return the ResponseEntity object that consist information of books.
	 * 
	 * @return
	 */
	@GetMapping
	@PermissionLibrarianAndMemberToRead
	public ResponseEntity<List<BookResponse>> findBooks() {

		return ResponseEntity.ok(bookService.findBooks());

	}

	/**
	 * Return the ResponseEntity object that consist information of book filter by
	 * id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/")
	@PermissionLibrarianAndMemberToRead
	public ResponseEntity<BookResponse> findById(@RequestParam Long id) {

		return ResponseEntity.ok(bookService.findBookById(id));

	}

	/**
	 * Return the ResponseEntity object that consist information of create new book
	 * response.
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "add-book/")
	@PermissionLibrarianToCreate
	public ResponseEntity<MessageResponse> addBook(@RequestBody AddBookRequest request) {

		bookService.addBook(request);

		return ResponseEntity
				.ok(new MessageResponse(String.format("Successfully created new book - %s", request.getBookTitle())));

	}

	/**
	 * Return the ResponseEntity object that consist information of update existing
	 * book response.
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(value = "update-book/")
	@PermissionLibrarianToUpdate
	public ResponseEntity<MessageResponse> updateBook(@RequestBody UpdateBookRequest request) {

		bookService.updateBook(request);

		return ResponseEntity.ok(new MessageResponse("Successfully update book!"));

	}

	/**
	 * Return the ResponseEntity object that consist information of delete existing
	 * user response.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "delete-book/")
	@PermissionLibrarianToDelete
	public ResponseEntity<MessageResponse> deleteBook(@RequestParam Long id) {

		bookService.deleteBookById(id);

		return ResponseEntity.ok(new MessageResponse(String.format("Successfully delete book - %d!", id)));

	}

	@PutMapping(value = "borrow/")
	@PermissionLibrarianAndMemberToUpdate
	public ResponseEntity<MessageResponse> borrowBook(@RequestHeader(name = "Authorization") String headerAuth,
			@RequestParam Long id) {

		bookService.borrowBookById(headerAuth, id);

		return ResponseEntity.ok(new MessageResponse(String.format("Successfully borrow book - %d!", id)));

	}

	@PutMapping(value = "return/")
	@PermissionLibrarianAndMemberToUpdate
	public ResponseEntity<MessageResponse> returnBook(@RequestParam Long id) {

		bookService.returnBookById(id);

		return ResponseEntity.ok(new MessageResponse(String.format("Successfully return book - %d!", id)));

	}

}
