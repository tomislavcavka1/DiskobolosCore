package hr.diskobolos.converter;

import java.io.Serializable;
import java.util.function.Function;

/**
 * A callback interface for providing values from a given source.
 * <p>
 * For example this interface can be implemented to simply extract a value with
 * a getter, or to create a composite value based on the fields of the source
 * object.
 *
 * @author Tomislav Čavka
 *
 * @param <SOURCE> the type of the object used to provide the value
 * @param <TARGET> the type of the provided value
 */
@FunctionalInterface
public interface ValueProvider<SOURCE, TARGET>
        extends Function<SOURCE, TARGET>, Serializable {

    /**
     * Returns a value provider that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    public static <T> ValueProvider<T, T> identity() {
        return t -> t;
    }

    /**
     * Provides a value from the given source object.
     *
     * @param source the source to retrieve the value from
     * @return the value provided by the source
     */
    @Override
    public TARGET apply(SOURCE source);
}
