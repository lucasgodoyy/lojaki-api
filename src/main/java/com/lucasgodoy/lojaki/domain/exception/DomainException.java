package com.lucasgodoy.lojaki.domain.exception;

/**
 * Exception used for business rule violations in the Domain layer.
 * Pure domain exception - no dependencies on frameworks or infrastructure.
 */
public class DomainException extends RuntimeException {

    /**
     * Creates a new DomainException with a message.
     *
     * @param message Description of the business rule violation.
     */
    public DomainException(String message) {
        super(message);
    }

    /**
     * Creates a new DomainException with a message and cause.
     *
     * @param message Description of the business rule violation.
     * @param cause   The original exception that caused this exception.
     */
    public DomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
