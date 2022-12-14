package hexlet.code.schemas;

import java.util.LinkedHashSet;
import java.util.function.BiPredicate;

import hexlet.code.Schema;

abstract class AbstractSchema<S extends Schema> implements Schema {

    protected LinkedHashSet<BiPredicate<S, Object>> constraints = new LinkedHashSet<>();

    @Override
    public boolean isValid(Object value) {
        for (var constraint : constraints) {
            if (!constraint.test((S) this, value)) {
                return false;
            }
        }

        return true;
    }
}
