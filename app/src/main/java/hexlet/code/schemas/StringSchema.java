package hexlet.code.schemas;

import java.util.function.BiPredicate;

import hexlet.code.Schema;

public final class StringSchema extends AbstractSchema<StringSchema> implements Schema, BaseSchema {
    private int minLength;
    private String substringToContain;

    public StringSchema() {
        constraints.add(Constraints.IS_STRING.isValid);
    }

    public StringSchema required() {
        constraints.add(Constraints.REQUIRED.isValid);
        return this;
    }

    public StringSchema minLength(Integer length) {
        constraints.add(Constraints.MIN_LENGTH.isValid);
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        constraints.add(Constraints.CONTAINS.isValid);
        this.substringToContain = substring;
        return this;
    }

    private enum Constraints {
        IS_STRING((schema, value) -> value instanceof String || value == null),
        REQUIRED((schema, value) -> value != null && ((String) value).length() > 0),
        MIN_LENGTH((schema, value) -> ((String) value).length() >= schema.minLength),
        CONTAINS((schema, value) -> ((String) value).contains(schema.substringToContain));
        private final BiPredicate<StringSchema, Object> isValid;

        Constraints(BiPredicate<StringSchema, Object> validate) {
            this.isValid = validate;
        }
    }
}
