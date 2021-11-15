package nl.han.aim.oosevt.lamport.shared;

import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;

public abstract class RequestDTO {
    private final InvalidDTOException invalidDtoException = new InvalidDTOException();

    public void validate() {
        validateDTO();
        if(!invalidDtoException.getErrors().isEmpty()) {
            throw invalidDtoException;
        }
    }

    protected void addError(String key, String value) {
        invalidDtoException.addError(key, value);
    }

    protected abstract void validateDTO();
}