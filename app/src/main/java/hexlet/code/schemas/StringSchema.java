package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import hexlet.code.Schema;

public class StringSchema implements Schema, BaseSchema {
    private final Set<Constraints> constraints = new HashSet<>(Set.of(Constraints.IS_STRING));
    private int minLength;
    private String substringToContain;

    @Override
    public boolean isValid(Object value) {
        for (Constraints constraint : Constraints.values()) {
            if (constraints.contains(constraint) && !constraint.isValid.test(this, value)) {
                return false;
            }
        }

        return true;
    }

    public StringSchema required() {
        constraints.add(Constraints.REQUIRED);
        return this;
    }

    public StringSchema minLength(Integer length) {
        constraints.add(Constraints.MIN_LENGTH);
        this.minLength = length;
        return this;
    }

    public Schema contains(String substring) {
        constraints.add(Constraints.CONTAINS);
        this.substringToContain = substring;
        return this;
    }

    private enum Constraints {
        IS_STRING((schema, value) -> value instanceof String || value == null),
        REQUIRED((schema, value) -> value != null && ((String) value).length() > 0),
        MIN_LENGTH((schema, value) -> ((String) value).length() >= schema.minLength),
        CONTAINS((schema, value) -> ((String) value).contains(schema.substringToContain));
        private final BiPredicate<StringSchema, Object> isValid;

        Constraints(BiPredicate<StringSchema, Object> isValid) {
            this.isValid = isValid;
        }
    }
}
