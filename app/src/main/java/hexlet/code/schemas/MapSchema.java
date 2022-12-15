package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;

public final class MapSchema extends BaseSchema {
    public MapSchema() {
        addCheck("", value -> value instanceof Map || value == null);
    }

    public MapSchema required() {
        addCheck("required", Objects::nonNull);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        addCheck("sizeof", value -> ((Map<?, ?>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema> shape) {
        addCheck("shape", value -> {
            Map<?, ?> checkedVal = (Map<?, ?>) value;
            for (Map.Entry<?, BaseSchema> baseSchema : shape.entrySet()) {
                if (!baseSchema.getValue().isValid(checkedVal.get(baseSchema.getKey()))) {
                    return false;
                }
            }

            return true;
        });

        return this;
    }
}
