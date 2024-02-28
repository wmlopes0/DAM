/**
 * 
 */
package javax.speech;

import java.security.Permission;


/**
 * Base implementation of a security check.
 * 
 * @author Dirk Schnelle-Walka
 * 
 */
public final class JavaSpeechSecurity {
    /**
     * Do not instantiate.
     */
    private JavaSpeechSecurity() {
    }

    /**
     * Checks if a {@link SecurityManager} is set and asks it for permission
     * to execute the named permission.
     * @param name name of the permission to check
     * @throws SecurityException
     *         if the named permission is not allowed to be executed 
     */
    public static void checkPermission(final String name)
            throws SecurityException {
        final SecurityManager security = System.getSecurityManager();
        if (security != null) {
            final Permission permission = new SpeechPermission(name);
            security.checkPermission(permission);
        }
    }
}
