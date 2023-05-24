package posmy.interview.boot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import posmy.interview.boot.model.BookEntity;
import posmy.interview.boot.model.EBookStatus;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;
import posmy.interview.boot.payload.response.BookResponse;
import posmy.interview.boot.respository.BookRepository;
import posmy.interview.boot.respository.UserRepository;
import posmy.interview.boot.security.JwtUtils;
import posmy.interview.boot.service.exception.BookException;
import posmy.interview.boot.service.exception.BookNotFoundException;
import posmy.interview.boot.service.exception.UpdateBookException;
import posmy.interview.boot.service.exception.UserException;

/**
 * Implementation of book service class.
 * 
 * @author mokht
 *
 */
@Service
public class BookServiceImpl implements BookService {

	/**
	 * 
	 */
	private final BookRepository bookRepository;

	/**
	 * 
	 */
	private final UserRepository userRepository;

	/**
	 * 
	 */
	private final JwtUtils jwtUtils;

	/**
	 * Class constructor.
	 * 
	 * @param bookRepository
	 * @param userRepository
	 * @param jwtUtils
	 */
	public BookServiceImpl(BookRepository bookRepository, UserRepository userRepository, JwtUtils jwtUtils) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.jwtUtils = jwtUtils;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookResponse> findBooks() {

		List<BookEntity> bookEntities = bookRepository.findAll();

		return bookEntities.stream().map(BookResponse::new).toList();

	}

	@Override
	@Transactional(readOnly = true)
	public BookResponse findBookById(Long bookId) {

		return bookRepository.findById(bookId).map(BookResponse::new)
				.orElseThrow(() -> new BookNotFoundException("Book not found!"));

	}

	@Override
	@Transactional
	public void addBook(AddBookRequest request) {

		// Save book
		BookEntity bookEntity = new BookEntity(request.getAuthor(), request.getBookTitle(),
				request.getBookDescription(), EBookStatus.AVAILABLE); // Default new book is available

		bookRepository.save(bookEntity);

	}

	@Override
	@Transactional
	public void updateBook(UpdateBookRequest request) {

		// Find book
		BookEntity bookEntity = bookRepository.findById(request.getId())
				.orElseThrow(() -> new UpdateBookException("Book not found!"));

		// Update book
		bookEntity.setAuthor(request.getAuthor());
		bookEntity.setBookTitle(request.getBookTitle());
		bookEntity.setBookDescription(request.getBookDescription());

		bookRepository.save(bookEntity);

	}

	@Override
	@Transactional
	public void deleteBookById(Long bookId) {

		// Validate book if there is someone borrowed or not
		BookEntity entity = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found!"));

		if (entity.getStatus().equals(EBookStatus.BORROWED)) {

			throw new BookException(String.format(
					"Book %d is still borrowed by someone. Please ensure to return the book first before delete.",
					bookId));

		}

		bookRepository.delete(entity);

	}

	@Override
	@Transactional
	public void borrowBookById(String headerAuth, Long bookId) {

		String token = "";

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer-")) {

			token = headerAuth.substring(7, headerAuth.length());

		}

		// Find user
		UserEntity userEntity = userRepository.findByEmail(jwtUtils.getUsernameFromToken(token))
				.orElseThrow(() -> new UserException("Invalid user!"));

		// Find book
		BookEntity bookEntity = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found!"));

		// Validate book if there is someone borrowed or not
		if (bookEntity.getStatus().equals(EBookStatus.BORROWED)) {

			throw new BookException(String.format("Book %d is not available to borrow.", bookId));

		}

		bookEntity.setStatus(EBookStatus.BORROWED);
		bookEntity.setBorrowedByUser(userEntity);

		bookRepository.save(bookEntity);

	}

	@Override
	@Transactional
	public void returnBookById(Long bookId) {

		// Find book
		BookEntity bookEntity = bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("Book not found!"));

		// Validate book if there is someone borrowed or not
		if (bookEntity.getStatus().equals(EBookStatus.AVAILABLE)) {

			throw new BookException(String.format("Book %d is already returned.", bookId));

		}

		bookEntity.setStatus(EBookStatus.AVAILABLE);
		bookEntity.setBorrowedByUser(null);

		bookRepository.save(bookEntity);

	}

}
