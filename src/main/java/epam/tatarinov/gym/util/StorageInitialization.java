package epam.tatarinov.gym.util;

import epam.tatarinov.gym.util.ClassType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface StorageInitialization {
    ClassType CLASS_TYPE();

}
