package hexlet.code.schemas;

import hexlet.code.Schema;

public final class StringSchema extends BaseSchema implements Schema {
    public StringSchema() {
        addCheck("isString", value -> value instanceof String || value == null);
    }

    public StringSchema required() {
        addCheck("required", value -> value != null && !((String) value).isEmpty());
        return this;
    }

    public StringSchema minLength(Integer minLength) {
        addCheck("minLength", value -> ((String) value).length() >= minLength);
        return this;
    }

    public StringSchema contains(String substring) {
        addCheck("contains", value -> ((String) value).contains(substring));
        return this;
    }
}
