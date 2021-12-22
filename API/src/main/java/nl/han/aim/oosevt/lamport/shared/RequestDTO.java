package nl.han.aim.oosevt.lamport.shared;

import nl.han.aim.oosevt.lamport.exceptions.InvalidDTOException;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MaxValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.MinValue;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.NotEmpty;
import nl.han.aim.oosevt.lamport.shared.validator.annotations.TranslatedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import java.lang.reflect.Field;
import java.util.logging.Logger;

public abstract class RequestDTO {
    private final InvalidDTOException invalidDtoException = new InvalidDTOException();
    private static final Logger LOGGER = Logger.getLogger(RequestDTO.class.getName());

    public void validate() {
        //We need all the fields that validate was called for. Because we got a structure with Create and Update request DTO, that inherit a superclass,
        //We would want the fields of that superclass too. We don't want RequestDTO, as that will return 1 field of an exception.
        Class<?> clazz = getClass();
        final ArrayList<Field> fields = new ArrayList<>();
        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        while(clazz.getSuperclass() != null && !clazz.getSuperclass().equals(RequestDTO.class)) {
            clazz = clazz.getSuperclass();
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
        }
        for(Field field : fields) {
            //Set the field to accessible to grab the value
            field.setAccessible(true);
            try {
                final String fieldName = field.getName();
                final String fieldDisplayName = field.isAnnotationPresent(TranslatedName.class) ? field.getAnnotation(TranslatedName.class).name() : field.getName();
                if (field.isAnnotationPresent(NotEmpty.class)) {
                    if (fieldIsEmpty(field, this)) {
                        addError(fieldName, String.format("%s mag niet leeg zijn!", fieldDisplayName));
                        continue;
                    }
                }
                if( field.getType().equals(int.class) ||
                        field.getType().equals(Integer.class) ||
                        field.getType().equals(Double.class) ||
                        field.getType().equals(double.class) ||
                        field.getType().equals(Float.class) ||
                        field.getType().equals(float.class)) {
                    validateNumberField(field, fieldName, fieldDisplayName);
                }
            } catch (IllegalAccessException e) {
                LOGGER.log(Level.SEVERE, "IllegalAccessException", e);
            }
        }
        if(!invalidDtoException.getErrors().isEmpty()) {
            throw invalidDtoException;
        }
    }

    protected void addError(String key, String value) {
        invalidDtoException.addError(key, value);
    }

    private boolean fieldIsEmpty(Field field, Object object) throws IllegalAccessException {
        //The field is empty if 'null', if the field is a string and the string is "" or if its a number field and the value is 0
        return  field.get(object) == null ||
                (field.getType().equals(String.class) && field.get(object).equals("")) ||
                ((field.getType().equals(int.class) || field.getType().equals(Integer.class)) && (int)field.get(object) == 0) ||
                ((field.getType().equals(double.class) || field.getType().equals(Double.class)) && (double)field.get(object) == 0) ||
                ((field.getType().equals(float.class) || field.getType().equals(Float.class)) && (float)field.get(object) == 0);
    }

    private void validateNumberField(Field field, String fieldName, String fieldDisplayName) throws IllegalAccessException {
        final double value = field.getDouble(this);
        if(field.isAnnotationPresent(MinValue.class)) {
            final int minValue = field.getAnnotation(MinValue.class).value();
            if(value < minValue) {
                addError(fieldName, String.format("%s moet minimaal %d zijn!", fieldDisplayName, minValue));
            }
        }
        if(field.isAnnotationPresent(MaxValue.class)) {
            final int maxValue = field.getAnnotation(MaxValue.class).value();
            if(value > maxValue) {
                addError(fieldName, String.format("%s mag maximaal %d zijn!", fieldName, maxValue));
            }
        }
    }
}