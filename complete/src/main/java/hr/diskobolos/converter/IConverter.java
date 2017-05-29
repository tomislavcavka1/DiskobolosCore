package hr.diskobolos.converter;

import java.io.Serializable;

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
public interface IConverter<SOURCE, TARGET> extends Serializable {

    /**
     * Converts from the source to the target object
     *
     * @param source the type of the source object
     * @return the type of the target object
     */
    TARGET convert(SOURCE source);
}
