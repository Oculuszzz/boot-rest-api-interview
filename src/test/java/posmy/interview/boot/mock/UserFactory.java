/**
 * 
 */
package posmy.interview.boot.mock;

import java.util.ArrayList;
import java.util.List;

import posmy.interview.boot.model.ERole;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;

/**
 * @author mokht
 *
 */
public final class UserFactory {

	private static UserFactory instance;

	public static synchronized UserFactory getInstance() {

		if (instance == null) {

			instance = new UserFactory();

		}

		return instance;

	}

	public UserEntity constructUserEntityMember() {

		return new UserEntity(1L, "test@gmail.com", "password", ERole.MEMBER);

	}
	
	public UserEntity constructExpectedUpdateUserEntityLibrarian() {

		return new UserEntity(1L, "update@gmail.com", "password", ERole.LIBRARIAN);

	}

	public List<UserEntity> constructUsersEntitiesMemberLibrarian() {

		List<UserEntity> users = new ArrayList<>();

		users.add(new UserEntity(1L, "member@gmail.com", "password", ERole.MEMBER));
		users.add(new UserEntity(2L, "librarian@gmail.com", "password", ERole.LIBRARIAN));

		return users;

	}

	public AddUserRequest constructAddUserMemberRequest() {

		return new AddUserRequest("member@gmail.com", "password", ERole.MEMBER);

	}
	
	public UpdateUserRequest constructUpdateUserLibrianRequest() {

		return new UpdateUserRequest("update@gmail.com", "password", ERole.LIBRARIAN);

	}

}
