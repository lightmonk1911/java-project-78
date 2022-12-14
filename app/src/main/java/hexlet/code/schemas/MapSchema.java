package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

import hexlet.code.Schema;

public class MapSchema implements Schema {
    private final Set<Constraints> constraints = new HashSet<>(Set.of(Constraints.IS_MAP));
    private int size;

    @Override
    public boolean isValid(Object value) {
        for (Constraints constraint : Constraints.values()) {
            if (constraints.contains(constraint) && !constraint.isValid.test(this, value)) {
                return false;
            }
        }

        return true;
    }

    public MapSchema required() {
        constraints.add(Constraints.REQUIRED);
        return this;
    }

    public MapSchema sizeof(Integer sizeValue) {
        constraints.add(Constraints.SIZE_OF);
        this.size = sizeValue;
        return this;
    }

    private enum Constraints {
        IS_MAP((schema, value) -> value instanceof Map || value == null),
        REQUIRED((schema, value) -> value != null),
        SIZE_OF((schema, value) -> ((Map<?, ?>) value).size() == schema.size);
        private final BiPredicate<MapSchema, Object> isValid;

        Constraints(BiPredicate<MapSchema, Object> isValid) {
            this.isValid = isValid;
        }
    }
}
