/**
 * 
 */
package posmy.interview.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import posmy.interview.boot.mock.BookFactory;
import posmy.interview.boot.model.BookEntity;
import posmy.interview.boot.model.EBookStatus;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;
import posmy.interview.boot.payload.response.BookResponse;
import posmy.interview.boot.respository.BookRepository;
import posmy.interview.boot.respository.UserRepository;
import posmy.interview.boot.security.JwtUtils;

/**
 * @author mokht
 *
 */
@ExtendWith(MockitoExtension.class) // for JUnit 5
class BookServiceImplTest {

	private BookServiceImpl underTest;

	@Mock
	private BookRepository bookRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private JwtUtils jwtUtils;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {

		underTest = new BookServiceImpl(bookRepository, userRepository, jwtUtils);

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#findBooks()}.
	 */
	@Test
	void testFindBooks() {

		// given
		List<BookEntity> mockBookEntities = BookFactory.getInstance().constructBooksEntities();
		List<BookResponse> expectedResult = mockBookEntities.stream().map(BookResponse::new).toList();

		given(bookRepository.findAll()).willReturn(mockBookEntities);

		// when
		List<BookResponse> result = underTest.findBooks();

		// then
		assertThat(result).hasSameSizeAs(expectedResult);

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#findBookById(java.lang.Long)}.
	 */
	@Test
	void testFindBookById() {

		// given
		Optional<BookEntity> mockBookEntity = Optional.of(BookFactory.getInstance().constructBooksEntityBorrowed());
		BookResponse expectedResult = mockBookEntity.map(BookResponse::new).get();
		given(bookRepository.findById(mockBookEntity.get().getId())).willReturn(mockBookEntity);

		// when
		BookResponse result = underTest.findBookById(mockBookEntity.get().getId());

		// then
		assertThat(result).isEqualTo(expectedResult);

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#addBook(posmy.interview.boot.payload.request.AddBookRequest)}.
	 */
	@Test
	void testAddBook() {

		// given
		AddBookRequest mockRequest = BookFactory.getInstance().constructAddBookRequest();
		BookEntity expectedResult = new BookEntity(mockRequest.getAuthor(), mockRequest.getBookTitle(),
				mockRequest.getBookDescription(), EBookStatus.AVAILABLE, null);

		// when
		underTest.addBook(mockRequest);

		// then
		ArgumentCaptor<BookEntity> userArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);
		verify(bookRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		BookEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#updateBook(posmy.interview.boot.payload.request.UpdateBookRequest)}.
	 */
	@Test
	void testUpdateBook() {

		// given
		UpdateBookRequest mockRequest = BookFactory.getInstance().constructUpdateBookAvailableRequest();
		Optional<BookEntity> mockBookEntity = Optional.of(BookFactory.getInstance().constructBooksEntityAvailable());
		BookEntity expectedResult = BookFactory.getInstance().constructExpectedUpdateBookAvailbleEntity();

		given(bookRepository.findById(mockRequest.getId())).willReturn(mockBookEntity);

		// when
		underTest.updateBook(mockRequest);

		// then
		ArgumentCaptor<BookEntity> userArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);
		verify(bookRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		BookEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#deleteBookById(java.lang.Long)}.
	 */
	@Test
	void testDeleteBookById() {

		// given
		Optional<BookEntity> mockBookEntity = Optional.of(BookFactory.getInstance().constructBooksEntityAvailable());

		given(bookRepository.findById(mockBookEntity.get().getId())).willReturn(mockBookEntity);

		// when
		underTest.deleteBookById(mockBookEntity.get().getId());

		// then
		verify(bookRepository, times(1)).delete(mockBookEntity.get());

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#borrowBookById(java.lang.String, java.lang.Long)}.
	 */
	@Test
	void testBorrowBookById() {

		// given
		String mockToken = "";

		Optional<UserEntity> mockUserEntity = Optional.of(BookFactory.getInstance().constructUserThatBorrowedBook());
		Optional<BookEntity> mockBookEntity = Optional.of(BookFactory.getInstance().constructBookAvailableToBeBorrow());
		BookEntity expectedResult = BookFactory.getInstance().constructBookAvailableBorrowSuccessfullyBorrowed();

		given(jwtUtils.getUsernameFromToken(mockToken)).willReturn(mockUserEntity.get().getEmail());
		given(userRepository.findByEmail(mockUserEntity.get().getEmail())).willReturn(mockUserEntity);
		given(bookRepository.findById(expectedResult.getId())).willReturn(mockBookEntity);

		// when
		underTest.borrowBookById(mockToken, mockBookEntity.get().getId());

		// then
		ArgumentCaptor<BookEntity> userArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);
		verify(bookRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		BookEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.service.BookServiceImpl#returnBookById(java.lang.Long)}.
	 */
	@Test
	void testReturnBookById() {

		// given
		Optional<BookEntity> mockBookEntity = Optional.of(BookFactory.getInstance().constructBookAvailableToBeReturn());
		BookEntity expectedResult = BookFactory.getInstance().constructBookBorrowedSuccessfullyReturn();

		given(bookRepository.findById(expectedResult.getId())).willReturn(mockBookEntity);

		// when
		underTest.returnBookById(mockBookEntity.get().getId());

		// then
		ArgumentCaptor<BookEntity> userArgumentCaptor = ArgumentCaptor.forClass(BookEntity.class);
		verify(bookRepository).save(userArgumentCaptor.capture()); // Capture argument created by mockito after save
																	// value in repository

		BookEntity response = userArgumentCaptor.getValue();

		assertThat(response).isEqualTo(expectedResult); // Compare result between ArgumentCaptor(after call
														// updateUserFeature()) and expected result.

	}

}
