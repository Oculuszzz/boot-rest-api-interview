package posmy.interview.boot.prepost;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author mokht
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyAuthority('librarian:delete', 'member:delete')")
public @interface PermissionLibrarianAndMemberToDelete {

}