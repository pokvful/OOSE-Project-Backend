package nl.han.aim.oosevt.lamport.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvalidDTOException extends RuntimeException {
    private final HashMap<String, List<String>> errors = new HashMap<>();

    public void addError(String key, String value) {
        if (errors.containsKey(key)) {
            final List<String> currentErrors = errors.get(key);
            currentErrors.add(value);
            errors.put(key, currentErrors);
            return;
        }
        errors.put(key, new ArrayList<>(List.of(value)));
    }

    public HashMap<String, List<String>> getErrors() {
        return errors;
    }
}