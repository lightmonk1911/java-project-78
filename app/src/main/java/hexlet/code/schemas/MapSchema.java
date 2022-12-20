package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {
    public MapSchema required() {
        addCheck("required", Map.class::isInstance);
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> ((Map<?, ?>) value).size() == size);
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema> schemas) {
        addCheck("shape", value -> schemas.entrySet()
                .stream()
                .allMatch(schemaEntry -> checkSchema(schemaEntry, (Map<?, ?>) value)));

        return this;
    }

    private boolean checkSchema(Map.Entry<?, BaseSchema> schemaEntry, Map<?, ?> value) {
        return schemaEntry.getValue().isValid(value.get(schemaEntry.getKey()));
    }
}
