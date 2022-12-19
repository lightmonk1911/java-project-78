package hexlet.code.schemas;

import hexlet.code.Schema;

public final class NumberSchema extends BaseSchema implements Schema {
    public NumberSchema positive() {
        addCheck("isPositive", value -> value == null || ((int) value) > 0);
        return this;
    }

    public NumberSchema required() {
        addCheck("required", Integer.class::isInstance);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.addCheck("range", value -> ((int) value) >= min && (int) value <= max);
        return this;
    }
}
