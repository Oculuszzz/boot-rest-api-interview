/**
 * 
 */
package posmy.interview.boot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import posmy.interview.boot.mock.BookFactory;
import posmy.interview.boot.mock.UserFactory;
import posmy.interview.boot.payload.request.AddBookRequest;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateBookRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.BookResponse;
import posmy.interview.boot.payload.response.UserResponse;
import posmy.interview.boot.service.BookServiceImpl;
import posmy.interview.boot.service.UserServiceImpl;

/**
 * @author mokht
 *
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
class BooksControllerTest {

	private static final String BOOKS_API = "/api/v1/books";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserServiceImpl userService;

	@MockBean
	private BookServiceImpl bookService;

	private ObjectMapper objectMapper = new ObjectMapper();

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

		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#findBooks()}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:read" })
	@Test
	void testFindBooksByLibririan() throws Exception {

		// given
		List<BookResponse> mockBooks = BookFactory.getInstance().constructBooksEntities().stream()
				.map(BookResponse::new).toList();

		given(bookService.findBooks()).willReturn(mockBooks);

		mockMvc.perform(get(BOOKS_API)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(mockBooks.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].author", is(mockBooks.get(0).getAuthor())))
				.andExpect(jsonPath("$[0].bookTitle", is(mockBooks.get(0).getBookTitle())))
				.andExpect(jsonPath("$[0].bookDescription", is(mockBooks.get(0).getBookDescription())))
				.andExpect(jsonPath("$[0].status", is(mockBooks.get(0).getStatus().name())))
				.andExpect(jsonPath("$[0].borrowByUserEmail", is(mockBooks.get(0).getBorrowByUserEmail())))
				.andExpect(jsonPath("$[1].id", is(mockBooks.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].author", is(mockBooks.get(1).getAuthor())))
				.andExpect(jsonPath("$[1].bookTitle", is(mockBooks.get(1).getBookTitle())))
				.andExpect(jsonPath("$[1].bookDescription", is(mockBooks.get(1).getBookDescription())))
				.andExpect(jsonPath("$[1].status", is(mockBooks.get(1).getStatus().name())))
				.andExpect(jsonPath("$[1].borrowByUserEmail", is(mockBooks.get(1).getBorrowByUserEmail())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#findBooks()}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:read" })
	@Test
	void testFindBooksByMember() throws Exception {

		// given
		List<BookResponse> mockBooks = BookFactory.getInstance().constructBooksEntities().stream()
				.map(BookResponse::new).toList();

		given(bookService.findBooks()).willReturn(mockBooks);

		mockMvc.perform(get(BOOKS_API)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(mockBooks.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].author", is(mockBooks.get(0).getAuthor())))
				.andExpect(jsonPath("$[0].bookTitle", is(mockBooks.get(0).getBookTitle())))
				.andExpect(jsonPath("$[0].bookDescription", is(mockBooks.get(0).getBookDescription())))
				.andExpect(jsonPath("$[0].status", is(mockBooks.get(0).getStatus().name())))
				.andExpect(jsonPath("$[0].borrowByUserEmail", is(mockBooks.get(0).getBorrowByUserEmail())))
				.andExpect(jsonPath("$[1].id", is(mockBooks.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].author", is(mockBooks.get(1).getAuthor())))
				.andExpect(jsonPath("$[1].bookTitle", is(mockBooks.get(1).getBookTitle())))
				.andExpect(jsonPath("$[1].bookDescription", is(mockBooks.get(1).getBookDescription())))
				.andExpect(jsonPath("$[1].status", is(mockBooks.get(1).getStatus().name())))
				.andExpect(jsonPath("$[1].borrowByUserEmail", is(mockBooks.get(1).getBorrowByUserEmail())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#findById(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:read" })
	@Test
	void testFindByIdByLabririan() throws Exception {

		// given
		BookResponse mockBook = Optional.of(BookFactory.getInstance().constructBooksEntityAvailable())
				.map(BookResponse::new).get();

		given(bookService.findBookById(mockBook.getId())).willReturn(mockBook);

		mockMvc.perform(get(BOOKS_API + "/?id=" + mockBook.getId().intValue())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockBook.getId().intValue())))
				.andExpect(jsonPath("$.author", is(mockBook.getAuthor())))
				.andExpect(jsonPath("$.bookTitle", is(mockBook.getBookTitle())))
				.andExpect(jsonPath("$.bookDescription", is(mockBook.getBookDescription())))
				.andExpect(jsonPath("$.status", is(mockBook.getStatus().name())))
				.andExpect(jsonPath("$.borrowByUserEmail", is(mockBook.getBorrowByUserEmail())))
				.andExpect(jsonPath("$.id", is(mockBook.getId().intValue())))
				.andExpect(jsonPath("$.author", is(mockBook.getAuthor())))
				.andExpect(jsonPath("$.bookTitle", is(mockBook.getBookTitle())))
				.andExpect(jsonPath("$.bookDescription", is(mockBook.getBookDescription())))
				.andExpect(jsonPath("$.status", is(mockBook.getStatus().name())))
				.andExpect(jsonPath("$.borrowByUserEmail", is(mockBook.getBorrowByUserEmail())));

	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#findById(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:read" })
	@Test
	void testFindByIdByMember() throws Exception {

		// given
		BookResponse mockBook = Optional.of(BookFactory.getInstance().constructBooksEntityAvailable())
				.map(BookResponse::new).get();

		given(bookService.findBookById(mockBook.getId())).willReturn(mockBook);

		mockMvc.perform(get(BOOKS_API + "/?id=" + mockBook.getId().intValue())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockBook.getId().intValue())))
				.andExpect(jsonPath("$.author", is(mockBook.getAuthor())))
				.andExpect(jsonPath("$.bookTitle", is(mockBook.getBookTitle())))
				.andExpect(jsonPath("$.bookDescription", is(mockBook.getBookDescription())))
				.andExpect(jsonPath("$.status", is(mockBook.getStatus().name())))
				.andExpect(jsonPath("$.borrowByUserEmail", is(mockBook.getBorrowByUserEmail())))
				.andExpect(jsonPath("$.id", is(mockBook.getId().intValue())))
				.andExpect(jsonPath("$.author", is(mockBook.getAuthor())))
				.andExpect(jsonPath("$.bookTitle", is(mockBook.getBookTitle())))
				.andExpect(jsonPath("$.bookDescription", is(mockBook.getBookDescription())))
				.andExpect(jsonPath("$.status", is(mockBook.getStatus().name())))
				.andExpect(jsonPath("$.borrowByUserEmail", is(mockBook.getBorrowByUserEmail())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#addBook(posmy.interview.boot.payload.request.AddBookRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:create" })
	void testAddBookByLibrian() throws JsonProcessingException, Exception {

		// given
		AddBookRequest request = BookFactory.getInstance().constructAddBookRequest();

		// when
		
		// then
        mockMvc.perform(post(BOOKS_API + "/add-book/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#addBook(posmy.interview.boot.payload.request.AddBookRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@Test
	@WithMockUser(username = "julia", password = "password", authorities = { "member:create" })
	void testAddBookByMember() throws JsonProcessingException, Exception {

		// given
		AddBookRequest request = BookFactory.getInstance().constructAddBookRequest();

		// when
		
		// then
        mockMvc.perform(post(BOOKS_API + "/add-book/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
	
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#updateBook(posmy.interview.boot.payload.request.UpdateBookRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:update" })
	@Test
	void testUpdateBookByLibririan() throws JsonProcessingException, Exception {

		// given
		UpdateBookRequest request = BookFactory.getInstance().constructUpdateBookAvailableRequest();

		// when
		
		// then
        mockMvc.perform(put(BOOKS_API + "/update-book/?id=1")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#updateBook(posmy.interview.boot.payload.request.UpdateBookRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:update" })
	@Test
	void testUpdateBookByMember() throws JsonProcessingException, Exception {

		// given
		UpdateBookRequest request = BookFactory.getInstance().constructUpdateBookAvailableRequest();

		// when
		
		// then
        mockMvc.perform(put(BOOKS_API + "/update-book/?id=1")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
	
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#deleteBook(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:delete" })
	@Test
	void testDeleteBookByLibririan() throws Exception {
		
		// then
		mockMvc.perform(delete(BOOKS_API + "/delete-book/?id=1"))
		.andExpect(status().isOk());
		
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#deleteBook(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:delete" })
	@Test
	void testDeleteBookByMember() throws Exception {
		
		// then
		mockMvc.perform(delete(BOOKS_API + "/delete-book/?id=1"))
		.andExpect(status().isForbidden());
		
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#borrowBook(java.lang.String, java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:update" })
	@Test
	void testBorrowBookByLibririan() throws Exception {

		// then
        mockMvc.perform(put(BOOKS_API + "/borrow/?id=1").header("Authorization", "token"))
                .andExpect(status().isOk());
	
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#borrowBook(java.lang.String, java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:update" })
	@Test
	void testBorrowBookByMember() throws Exception {

		// then
        mockMvc.perform(put(BOOKS_API + "/borrow/?id=1").header("Authorization", "token"))
                .andExpect(status().isOk());
	
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#returnBook(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:update" })
	@Test
	void testReturnBookByLibririan() throws Exception {
		
		// then
        mockMvc.perform(put(BOOKS_API + "/return/?id=1").header("Authorization", "token"))
                .andExpect(status().isOk());
		
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.BooksController#returnBook(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:update" })
	@Test
	void testReturnBookByMember() throws Exception {
		
		// then
        mockMvc.perform(put(BOOKS_API + "/return/?id=1").header("Authorization", "token"))
                .andExpect(status().isOk());
		
	}

}
