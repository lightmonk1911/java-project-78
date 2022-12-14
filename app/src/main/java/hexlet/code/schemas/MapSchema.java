package hexlet.code.schemas;

import java.util.Map;
import java.util.function.BiPredicate;

import hexlet.code.Schema;

public final class MapSchema extends AbstractSchema<MapSchema> implements Schema {
    private int size;
    private Map<?, BaseSchema> shape;

    public MapSchema() {
        constraints.add(Constraints.IS_MAP.isValid);
    }

    public MapSchema required() {
        constraints.add(Constraints.REQUIRED.isValid);
        return this;
    }

    public MapSchema sizeof(Integer sizeValue) {
        constraints.add(Constraints.SIZE_OF.isValid);
        this.size = sizeValue;
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema> schemas) {
        this.shape = schemas;
        constraints.add(Constraints.SHAPE.isValid);
        return this;
    }

    private enum Constraints {
        IS_MAP((schema, value) -> value instanceof Map || value == null),
        REQUIRED((schema, value) -> value != null),
        SIZE_OF((schema, value) -> ((Map<?, ?>) value).size() == schema.size),
        SHAPE((schema, value) -> {
            Map<?, ?> checkedVal = (Map<?, ?>) value;
            for (Map.Entry<?, BaseSchema> baseSchema : schema.shape.entrySet()) {
                if (!baseSchema.getValue().isValid(checkedVal.get(baseSchema.getKey()))) {
                    return false;
                }
            }

            return true;
        });
        private final BiPredicate<MapSchema, Object> isValid;

        Constraints(BiPredicate<MapSchema, Object> validate) {
            this.isValid = validate;
        }
    }
}
