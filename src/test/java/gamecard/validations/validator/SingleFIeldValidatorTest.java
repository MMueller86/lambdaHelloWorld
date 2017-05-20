package gamecard.validations.validator;

import gamecard.validations.annotations.SingleFieldValid;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationDescriptor;
import org.hibernate.validator.internal.util.annotationfactory.AnnotationFactory;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by mimo on 20.05.2017.
 */
public class SingleFIeldValidatorTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void isValidDigit2() {
        final SingleFieldValidator validator = new SingleFieldValidator();
        validator.initialize(createAnnotation(2));
        Assert.assertFalse(validator.isValid(1, null));
        Assert.assertFalse(validator.isValid(13, null));
        Assert.assertFalse(validator.isValid(3, null));
        Assert.assertTrue(validator.isValid(0, null));
        Assert.assertTrue(validator.isValid(null, null));
        Assert.assertTrue(validator.isValid(2, null));
        Assert.assertTrue(validator.isValid(4, null));
        Assert.assertTrue(validator.isValid(6, null));
        Assert.assertTrue(validator.isValid(8, null));
        Assert.assertTrue(validator.isValid(10, null));
        Assert.assertTrue(validator.isValid(12, null));
    }

    @Test
    public void isValidDigit3() {
        final SingleFieldValidator validator = new SingleFieldValidator();
        validator.initialize(createAnnotation(3));
        Assert.assertFalse(validator.isValid(1, null));
        Assert.assertFalse(validator.isValid(19, null));
        Assert.assertFalse(validator.isValid(4, null));
        Assert.assertFalse(validator.isValid(21, null));
        Assert.assertTrue(validator.isValid(0, null));
        Assert.assertTrue(validator.isValid(null, null));
        Assert.assertTrue(validator.isValid(3, null));
        Assert.assertTrue(validator.isValid(6, null));
        Assert.assertTrue(validator.isValid(9, null));
        Assert.assertTrue(validator.isValid(12, null));
        Assert.assertTrue(validator.isValid(15, null));
        Assert.assertTrue(validator.isValid(18, null));
    }

    @Test
    public void isValid_IllegalArgumentException() {
        final SingleFieldValidator validator = new SingleFieldValidator();
        thrown.expect(IllegalArgumentException.class);
        validator.initialize(createAnnotation(7));
    }

    @Test
    public void isValid_IllegalArgumentExceptionZero() {
        final SingleFieldValidator validator = new SingleFieldValidator();
        thrown.expect(IllegalArgumentException.class);
        validator.initialize(createAnnotation(0));
    }


    private SingleFieldValid createAnnotation(final int value) {
        final AnnotationDescriptor<SingleFieldValid> descriptor = new AnnotationDescriptor<SingleFieldValid>(SingleFieldValid.class);
        descriptor.setValue("baseDigit", value);

        return AnnotationFactory.create(descriptor);
    }
}
