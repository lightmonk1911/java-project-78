package hexlet.code.schemas;

import java.util.Objects;

import hexlet.code.Schema;

public final class NumberSchema extends BaseSchema implements Schema {
    public NumberSchema() {
        addCheck("isNumber", value -> value == null || value instanceof Number);
    }

    public NumberSchema positive() {
        addCheck("isPositive", value -> value == null || ((int) value) > 0);
        return this;
    }

    public NumberSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addCheck("range", value -> value == null || ((int) value) >= min && (int) value <= max);
        return this;
    }
}
