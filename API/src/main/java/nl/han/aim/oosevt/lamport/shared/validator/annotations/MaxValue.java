package nl.han.aim.oosevt.lamport.shared.validator.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MaxValue {
    int value();
}
