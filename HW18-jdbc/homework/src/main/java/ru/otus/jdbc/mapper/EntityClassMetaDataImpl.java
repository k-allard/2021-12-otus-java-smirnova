package ru.otus.jdbc.mapper;

import ru.otus.crm.model.annotations.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final String name;

    private final List<Field> allFields;

    private final Field idField;

    private final List<Field> fieldsWithoutId;

    private Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        allFields = List.of(clazz.getDeclaredFields());

        name = clazz.getSimpleName();

        idField = allFields.stream()
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findAny()
                .orElseThrow(() -> new RuntimeException("Id field not found"));

        fieldsWithoutId = allFields.stream()
                .filter(field -> !field.isAnnotationPresent(Id.class))
                .collect(Collectors.toList());

        for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameterTypes().length == allFields.size()) {
                this.constructor = (Constructor<T>) constructor;
                break;
            }
        }
        if (constructor == null)
            throw new RuntimeException("All args constructor not found");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Constructor<T> getConstructor() {
        return constructor;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    @Override
    public List<Field> getAllFields() {
        return allFields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return fieldsWithoutId;
    }
}
