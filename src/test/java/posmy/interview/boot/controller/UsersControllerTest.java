/**
 * 
 */
package posmy.interview.boot.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.*;

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

import posmy.interview.boot.mock.UserFactory;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.UserResponse;
import posmy.interview.boot.service.UserServiceImpl;

/**
 * @author mokht
 *
 */
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@AutoConfigureMockMvc
class UsersControllerTest {

	private static final String USERS_API = "/api/v1/users";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@MockBean
	private UserServiceImpl userService;

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
	 * {@link posmy.interview.boot.controller.UsersController#findUsers()}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:read" })
	@Test
	void testFindUsersByLibrarian() throws Exception {

		// given
		List<UserResponse> mockListUsers = UserFactory.getInstance().constructUsersEntitiesMemberLibrarian().stream()
				.map(UserResponse::new).toList();

		given(userService.findUsers()).willReturn(mockListUsers);

		mockMvc.perform(get(USERS_API)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(mockListUsers.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].email", is(mockListUsers.get(0).getEmail())))
				.andExpect(jsonPath("$[0].roles", hasSize(mockListUsers.get(0).getRoles().size())))
				.andExpect(jsonPath("$[1].id", is(mockListUsers.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].email", is(mockListUsers.get(1).getEmail())))
				.andExpect(jsonPath("$[1].roles", hasSize(mockListUsers.get(1).getRoles().size())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#findUsers()}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:read" })
	@Test
	void testFindUsersByMember() throws Exception {

		// given
		List<UserResponse> mockListUsers = UserFactory.getInstance().constructUsersEntitiesMemberLibrarian().stream()
				.map(UserResponse::new).toList();

		given(userService.findUsers()).willReturn(mockListUsers);

		// when
		// then

		mockMvc.perform(get(USERS_API)).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(mockListUsers.get(0).getId().intValue())))
				.andExpect(jsonPath("$[0].email", is(mockListUsers.get(0).getEmail())))
				.andExpect(jsonPath("$[0].roles", hasSize(mockListUsers.get(0).getRoles().size())))
				.andExpect(jsonPath("$[1].id", is(mockListUsers.get(1).getId().intValue())))
				.andExpect(jsonPath("$[1].email", is(mockListUsers.get(1).getEmail())))
				.andExpect(jsonPath("$[1].roles", hasSize(mockListUsers.get(1).getRoles().size())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#findById(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:read" })
	@Test
	void testFindByIdByLibrarian() throws Exception {

		// given
		UserResponse mockUser = Optional.of(UserFactory.getInstance().constructUserEntityMember())
				.map(UserResponse::new).get();

		given(userService.findUserById(mockUser.getId())).willReturn(mockUser);

		// when
		// then
		mockMvc.perform(get(USERS_API + "/?id=" + mockUser.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockUser.getId().intValue())))
				.andExpect(jsonPath("$.email", is(mockUser.getEmail())))
				.andExpect(jsonPath("$.roles", hasSize(mockUser.getRoles().size())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#findById(java.lang.Long)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:read" })
	@Test
	void testFindByIdByMember() throws Exception {

		// given
		UserResponse mockUser = Optional.of(UserFactory.getInstance().constructUserEntityMember())
				.map(UserResponse::new).get();

		given(userService.findUserById(mockUser.getId())).willReturn(mockUser);

		// when
		// then
		mockMvc.perform(get(USERS_API + "/?id=" + mockUser.getId())).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(mockUser.getId().intValue())))
				.andExpect(jsonPath("$.email", is(mockUser.getEmail())))
				.andExpect(jsonPath("$.roles", hasSize(mockUser.getRoles().size())));

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#addUser(posmy.interview.boot.payload.request.AddUserRequest)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:create" })
	@Test
	void testAddUserByLibrarian() throws Exception {

		// given
		AddUserRequest request = UserFactory.getInstance().constructAddUserMemberRequest();

		// when
		
		// then
        mockMvc.perform(post(USERS_API + "/add-user/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#addUser(posmy.interview.boot.payload.request.AddUserRequest)}.
	 * 
	 * @throws Exception
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:create" })
	@Test
	void testAddUserByMember() throws Exception {

		// given
		AddUserRequest request = UserFactory.getInstance().constructAddUserMemberRequest();

		// when
		
		// then
        mockMvc.perform(post(USERS_API + "/add-user/")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#updateUser(java.lang.Long, posmy.interview.boot.payload.request.UpdateUserRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:update" })
	@Test
	void testUpdateUserByLibrarian() throws JsonProcessingException, Exception {

		// given
		UpdateUserRequest request = UserFactory.getInstance().constructUpdateUserLibrarianRequest();

		// when
		
		// then
        mockMvc.perform(put(USERS_API + "/update-user/?id=1")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#updateUser(java.lang.Long, posmy.interview.boot.payload.request.UpdateUserRequest)}.
	 * @throws Exception 
	 * @throws JsonProcessingException 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:update" })
	@Test
	void testUpdateUserByMember() throws JsonProcessingException, Exception {

		// given
		UpdateUserRequest request = UserFactory.getInstance().constructUpdateUserLibrarianRequest();

		// when
		
		// then
        mockMvc.perform(put(USERS_API + "/update-user/?id=1")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
	
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#deleteUser(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:delete" })
	@Test
	void testDeleteUserByLibrarian() throws Exception {
		
		// then
		mockMvc.perform(delete(USERS_API + "/delete-user/?id=1"))
		.andExpect(status().isOk());
		
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#deleteUser(java.lang.Long)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "julia", password = "password", authorities = { "member:delete" })
	@Test
	void testDeleteUserByMember() throws Exception {
		
		// then
		mockMvc.perform(delete(USERS_API + "/delete-user/?id=1"))
		.andExpect(status().isForbidden());
		
	}

	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#deleteOwnUser(java.lang.String)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:delete" })
	@Test
	void testDeleteOwnUserByLibrarian() throws Exception {
		
		// then
		mockMvc.perform(delete(USERS_API + "/delete-own-user/")
				.header("Authorization", "token"))
		.andExpect(status().isOk());
		
	}
	
	/**
	 * Test method for
	 * {@link posmy.interview.boot.controller.UsersController#deleteOwnUser(java.lang.String)}.
	 * @throws Exception 
	 */
	@WithMockUser(username = "alex", password = "password", authorities = { "librarian:delete" })
	@Test
	void testDeleteOwnUserByMember() throws Exception {
		
		// then
		mockMvc.perform(delete(USERS_API + "/delete-own-user/")
				.header("Authorization", "token"))
		.andExpect(status().isOk());
		
	}

}
