package hexlet.code.schemas;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;

import hexlet.code.Schema;

public class NumberSchema implements Schema, BaseSchema {
    private final Set<Constraints> constraints = new HashSet<>(Set.of(Constraints.IS_NUMBER));
    private int min;
    private int max;

    @Override
    public boolean isValid(Object value) {
        for (Constraints constraint : Constraints.values()) {
            if (constraints.contains(constraint) && !constraint.isValid.test(this, value)) {
                return false;
            }
        }

        return true;
    }

    public NumberSchema positive() {
        this.constraints.add(Constraints.POSITIVE);
        return this;
    }

    public NumberSchema required() {
        this.constraints.add(Constraints.REQUIRED);
        return this;
    }

    public NumberSchema range(int minValue, int maxValue) {
        this.min = minValue;
        this.max = maxValue;
        this.constraints.add(Constraints.RANGE);
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
