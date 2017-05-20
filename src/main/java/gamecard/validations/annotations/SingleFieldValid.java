package gamecard.validations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Created by mimo on 20.05.2017.
 */
@Target(ElementType.FIELD)
public @interface SingleFieldValid {
    int baseDigit();
}
