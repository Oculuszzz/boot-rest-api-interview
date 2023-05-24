/**
 * 
 */
package posmy.interview.boot.mock;

import java.util.ArrayList;
import java.util.List;

import posmy.interview.boot.model.BookEntity;
import posmy.interview.boot.model.EBookStatus;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;

/**
 * @author mokht
 *
 */
public final class BookFactory {

	private static BookFactory instance;

	public static synchronized BookFactory getInstance() {

		if (instance == null) {

			instance = new BookFactory();

		}

		return instance;

	}

	public BookEntity constructBooksEntityAvailable() {

		return new BookEntity(1L, "author1", "Title1", "Description1", EBookStatus.AVAILABLE, null);

	}

	public BookEntity constructBooksEntityBorrowed() {

		return new BookEntity(2L, "author2", "Title2", "Description2", EBookStatus.BORROWED,
				constructUserThatBorrowedBook());

	}

	public UserEntity constructUserThatBorrowedBook() {

		return UserFactory.getInstance().constructUserEntityMember();

	}

	public List<BookEntity> constructBooksEntities() {

		List<BookEntity> books = new ArrayList<>();
		books.add(constructBooksEntityAvailable());
		books.add(constructBooksEntityBorrowed());

		return books;

	}

	public AddBookRequest constructAddBookRequest() {

		return new AddBookRequest("Author1", "Title1", "Description1");

	}

	public UpdateBookRequest constructUpdateBookAvailableRequest() {

		return new UpdateBookRequest(1L, "UpdateAuthor1", "UpdateTitle1", "UpdateDescription1");

	}

	public BookEntity constructExpectedUpdateBookAvailbleEntity() {

		return new BookEntity(1L, "UpdateAuthor1", "UpdateTitle1", "UpdateDescription1", EBookStatus.AVAILABLE, null);

	}

	public BookEntity constructBookAvailableToBeBorrow() {

		return new BookEntity(1L, "author1", "Title1", "Description1", EBookStatus.AVAILABLE, null);

	}

	public BookEntity constructBookAvailableBorrowSuccessfullyBorrowed() {

		return new BookEntity(1L, "author1", "Title1", "Description1", EBookStatus.BORROWED,
				constructUserThatBorrowedBook());
	}

	public BookEntity constructBookAvailableToBeReturn() {

		return new BookEntity(1L, "author1", "Title1", "Description1", EBookStatus.BORROWED,
				constructUserThatBorrowedBook());

	}

	public BookEntity constructBookBorrowedSuccessfullyReturn() {

		return new BookEntity(1L, "author1", "Title1", "Description1", EBookStatus.AVAILABLE, null);
	}

}
