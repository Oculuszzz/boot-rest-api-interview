/**
 * 
 */
package posmy.interview.boot.model;

/**
 * @author mokht
 *
 */
public enum EPermission {

	LIBRARIAN_READ("librarian:read"), 
	LIBRARIAN_UPDATE("librarian:update"), 
	LIBRARIAN_CREATE("librarian:create"), 
	LIBRARIAN_DELETE("librarian:delete"),
	MEMBER_READ("member:read"), 
	MEMBER_UPDATE("member:update"), 
	MEMBER_CREATE("member:create"),
	MEMBER_DELETE("member:delete");

	/**
	 * 
	 */
	private final String permission;
	
    /**
     * @param permission
     */
    private EPermission(String permission) {
        this.permission = permission;
    }

	/**
	 * @return the permission
	 */
	public String getPermission() {
		return permission;
	}


}
