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

import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.MessageResponse;
import posmy.interview.boot.payload.response.UserResponse;
import posmy.interview.boot.prepost.PermissionLibrarianAndMemberToDelete;
import posmy.interview.boot.prepost.PermissionLibrarianAndMemberToRead;
import posmy.interview.boot.prepost.PermissionLibrarianToCreate;
import posmy.interview.boot.prepost.PermissionLibrarianToDelete;
import posmy.interview.boot.prepost.PermissionLibrarianToUpdate;
import posmy.interview.boot.service.UserServiceImpl;

/**
 * Users controller class.
 * 
 * @author mokht
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAnyRole('LIBRARIAN', 'MEMBER')")
public class UsersController {

	/**
	 * The user service object.
	 */
	private final UserServiceImpl userService;

	/**
	 * Class constructor.
	 * 
	 * @param userService
	 */
	public UsersController(UserServiceImpl userService) {

		this.userService = userService;

	}

	/**
	 * Return the ResponseEntity object that consist information of users.
	 * 
	 * @return
	 */
	@GetMapping
	@PermissionLibrarianAndMemberToRead
	public ResponseEntity<List<UserResponse>> findUsers() {

		return ResponseEntity.ok(userService.findUsers());

	}

	/**
	 * Return the ResponseEntity object that consist information of user filter by
	 * id.
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/")
	@PermissionLibrarianAndMemberToRead
	public ResponseEntity<UserResponse> findById(@RequestParam Long id) {

		return ResponseEntity.ok(userService.findUserById(id));

	}

	/**
	 * Return the ResponseEntity object that consist information of create new user
	 * response.
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping(value = "add-user/")
	@PermissionLibrarianToCreate
	public ResponseEntity<MessageResponse> addUser(@RequestBody AddUserRequest request) {

		userService.addUser(request);

		return ResponseEntity
				.ok(new MessageResponse(String.format("Successfully created new user - %s", request.getEmail())));

	}

	/**
	 * Return the ResponseEntity object that consist information of update existing
	 * user response.
	 * 
	 * @param request
	 * @return
	 */
	@PutMapping(value = "update-user/")
	@PermissionLibrarianToUpdate
	public ResponseEntity<MessageResponse> updateUser(@RequestParam Long id, @RequestBody UpdateUserRequest request) {

		userService.updateUser(id, request);

		return ResponseEntity.ok(new MessageResponse("Successfully update user!"));

	}

	/**
	 * Return the ResponseEntity object that consist information of delete existing
	 * user response.
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "delete-user/")
	@PermissionLibrarianToDelete
	public ResponseEntity<MessageResponse> deleteUser(@RequestParam Long id) {

		userService.deleteUserById(id);

		return ResponseEntity.ok(new MessageResponse(String.format("Successfully delete user - %d!", id)));

	}

	@DeleteMapping(value = "delete-own-user/")
	@PermissionLibrarianAndMemberToDelete
	public ResponseEntity<MessageResponse> deleteOwnUser(@RequestHeader(name = "Authorization") String headerAuth) {

		userService.deleteOwnUser(headerAuth);

		return ResponseEntity.ok(new MessageResponse("Successfully deleted own account!"));

	}

}
