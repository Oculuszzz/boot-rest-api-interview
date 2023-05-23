/**
 * 
 */
package posmy.interview.boot.service;

import java.util.List;

import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.payload.response.UserResponse;

/**
 * User service interface class.
 * 
 * @author mokht
 *
 */
public interface UserService {

	/**
	 * Responsible to find all users.
	 * 
	 * @return
	 */
	public List<UserResponse> findUsers();

	/**
	 * Responsible to find user by user id.
	 * 
	 * @param userId
	 * @return
	 */
	public UserResponse findUserById(Long userId);

	/**
	 * Responsible to create an new user.
	 * 
	 * @param addUserRequest
	 */
	public void addUser(AddUserRequest addUserRequest);

	/**
	 * Responsible to update an existing user.
	 * 
	 * @param id
	 * @param updateUserRequest
	 */
	public void updateUser(Long id, UpdateUserRequest updateUserRequest);

	/**
	 * Responsible to delete/remove an existing user.
	 * 
	 * @param userId
	 */
	public void deleteUserById(Long userId);

	/**
	 * Delete own account.
	 * 
	 * @param token
	 */
	public void deleteOwnUser(String token);

}
