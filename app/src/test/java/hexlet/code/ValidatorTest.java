package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
final class ValidatorTest {

    private Validator validator;

    @BeforeAll
    void init() {
        validator = new Validator();
    }

    @Test
    void stringSchema() {

        StringSchema schema = validator.string();

        assertFalse(schema.isValid(1));

        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));

        schema.required();

        assertTrue(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("hexlet"));
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(5));
        assertFalse(schema.isValid(""));

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));

        assertFalse(schema.isValid("what does the fox say"));
        assertTrue(schema.isValid("whatthe"));

        schema.minLength(10);
        assertFalse(schema.isValid("whatthe"));
        assertTrue(schema.isValid("whatthewhatthe"));
    }
}
