package nl.han.aim.oosevt.lamport.shared;

import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InvalidDTOExceptionTests {
    @Test
    public void invalidDtoDoubleErrorOnFieldGetsAdded() {
        final InvalidDTOException invalidDTOException = new InvalidDTOException();

        invalidDTOException.addError("key", "value");
        invalidDTOException.addError("key", "value2");

        Assertions.assertEquals(2, invalidDTOException.getErrors().get("key").size());
    }
}
