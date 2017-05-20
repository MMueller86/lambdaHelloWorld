package gamecard.validations.validator;

import gamecard.validations.annotations.SingleFieldValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mimo on 20.05.2017.
 */
public class SingleField_validator implements ConstraintValidator<SingleFieldValid, Integer> {
    int minValue;
    int maxValue;

    @Override
    public void initialize(final SingleFieldValid constraintAnnotation) {
        final int baseDigit = constraintAnnotation.baseDigit();
        if (baseDigit < 1 || baseDigit > 6) {
            throw new IllegalArgumentException("Invalid use of annotation. Value between 1 and 6 required");
        }
        minValue = baseDigit;
        maxValue = 6 * baseDigit;
    }

    @Override
    public boolean isValid(final Integer value, final ConstraintValidatorContext context) {
        Boolean valid = Boolean.FALSE;

        if (null == value || 0 == value) {
            valid = Boolean.TRUE;
        } else {
            final int rest = value % minValue;
            if (rest == 0 && value <= maxValue && value >= minValue) {
                valid = true;
            }
        }
        return valid;
    }
}
