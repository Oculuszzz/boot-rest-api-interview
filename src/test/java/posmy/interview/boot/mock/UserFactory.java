/**
 * 
 */
package posmy.interview.boot.mock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import posmy.interview.boot.model.ERole;
import posmy.interview.boot.model.UserEntity;
import posmy.interview.boot.payload.request.AddUserRequest;
import posmy.interview.boot.payload.request.UpdateUserRequest;
import posmy.interview.boot.userdetails.UserDetailsImpl;

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

		return new UserEntity(1L, "member@gmail.com", "password", ERole.MEMBER);

	}

	public UserEntity constructUserEntityLibrian() {

		return new UserEntity(2L, "librian@gmail.com", "password", ERole.LIBRARIAN);

	}

	public UserEntity constructExpectedUpdateUserEntityLibrarian() {

		return new UserEntity(2L, "update@gmail.com", "password", ERole.LIBRARIAN);

	}

	public List<UserEntity> constructUsersEntitiesMemberLibrarian() {

		List<UserEntity> users = new ArrayList<>();

		users.add(constructUserEntityMember());
		users.add(constructUserEntityLibrian());

		return users;

	}

	public AddUserRequest constructAddUserMemberRequest() {

		return new AddUserRequest("member@gmail.com", "password", ERole.MEMBER);

	}

	public UpdateUserRequest constructUpdateUserLibrianRequest() {

		return new UpdateUserRequest("update@gmail.com", "password", ERole.LIBRARIAN);

	}

	public UserDetailsImpl constructUserLibrarianUserDetails() {

		UserEntity mockUser = constructUserEntityLibrian();

		return new UserDetailsImpl(mockUser.getId(), mockUser.getEmail(), mockUser.getPassword(),
				mockUser.getRole().getAuthorities());

	}

}
