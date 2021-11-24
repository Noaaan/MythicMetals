package nourl.mythicmetals.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.BiConsumer;

public class FieldIterator {
    /**
     * Iterates all accessible fields of the given object and
     * calls the field consumer on each applicable one
     *
     * @param object          The target object
     * @param targetFieldType The field type match
     * @param fieldConsumer   The function to apply to each field, supplied
     *                        with the field's value and ID
     * @param <C>             The type of {@code clazz}
     * @param <F>             The type of field to match
     */
    @SuppressWarnings("unchecked")
    public static <C, F> void iterateAccessibleFields(C object, Class<F> targetFieldType, BiConsumer<F, String> fieldConsumer) {
        for (var field : object.getClass().getDeclaredFields()) {
            if (Modifier.isStatic(field.getModifiers())) continue;

            F value;
            try {
                value = (F) field.get(object);
            } catch (IllegalAccessException e) {
                continue;
            }

            if (!targetFieldType.isAssignableFrom(value.getClass())) continue;
            var fieldId = field.getName().toLowerCase();

            fieldConsumer.accept(value, fieldId);
        }
    }
}
