package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.function.Predicate;

import hexlet.code.Schema;

public abstract class BaseSchema implements Schema {

    protected LinkedHashMap<String, Predicate<Object>> checks = new LinkedHashMap<>();
    /**
     * Determines if the given object is valid.
     *
     * @param value The value to be validated.
     * @return true if the object is valid, false otherwise.
     */
    @Override
    public boolean isValid(Object value) {
        for (var constraint : checks.values()) {
            if (!constraint.test(value)) {
                return false;
            }
        }

        return true;
    }

    protected final void addCheck(String name, Predicate<Object> check) {
        checks.put(name, check);
    }
}
