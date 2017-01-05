package com.angkasa.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author <a href="mailto:hazlan@gmail.com">Hazlan Rozaimi</a>
 */
public class MemberAlreadyHasUserException extends Exception {
    private static final long serialVersionUID = 4050482305178810162L;

    /**
     * Constructor for UserExistsException.
     *
     * @param message exception message
     */
    public MemberAlreadyHasUserException(final String message) {
        super(message);
    }
}
