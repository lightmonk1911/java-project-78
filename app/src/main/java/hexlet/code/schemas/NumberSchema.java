package hexlet.code.schemas;

import java.util.function.BiPredicate;

import hexlet.code.Schema;

public class NumberSchema extends AbstractSchema<NumberSchema> implements Schema, BaseSchema {
    private int min;
    private int max;

    public NumberSchema() {
        this.constraints.add(Constraints.IS_NUMBER.isValid);
    }

    public NumberSchema positive() {
        this.constraints.add(Constraints.POSITIVE.isValid);
        return this;
    }

    public NumberSchema required() {
        this.constraints.add(Constraints.REQUIRED.isValid);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        this.min = minValue;
        this.max = maxValue;
        this.constraints.add(Constraints.RANGE.isValid);
        return this;
    }

    private enum Constraints {
        IS_NUMBER((schema, value) -> value == null || value instanceof Number),
        REQUIRED((schema, value) -> value != null),
        POSITIVE((schema, value) -> value == null || ((int) value) > 0),
        RANGE((schema, value) -> value == null || ((int) value) >= schema.min && (int) value <= schema.max);

        private final BiPredicate<NumberSchema, Object> isValid;

        Constraints(BiPredicate<NumberSchema, Object> isValid) {
            this.isValid = isValid;
        }
    }
}
