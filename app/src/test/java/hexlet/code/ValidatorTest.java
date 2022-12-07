package hexlet.code;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class ValidatorTest {
    @Test
    void checkTestsWork() throws Exception {
        assertEquals(1, 1);
    }
}
