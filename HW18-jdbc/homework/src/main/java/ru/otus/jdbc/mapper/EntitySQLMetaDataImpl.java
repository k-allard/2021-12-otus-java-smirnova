package ru.otus.jdbc.mapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class EntitySQLMetaDataImpl implements EntitySQLMetaData {
    private final String selectAllSql;
    private final String selectByIdSql;
    private final String insertSql;
    private final String updateSql;

    public EntitySQLMetaDataImpl(EntityClassMetaData<?> entityClassMetaDataClient) {
        String name = entityClassMetaDataClient.getName();
        String id = entityClassMetaDataClient.getIdField().getName();
        List<Field> fieldsWithoutId = entityClassMetaDataClient.getFieldsWithoutId();
        String insertParams = fieldsWithoutId.stream()
                .map(Field::getName)
                .collect(Collectors.joining(", "));
        String insertValues = fieldsWithoutId.stream()
                .map(field -> "?")
                .collect(Collectors.joining(", "));
        String updateParams = fieldsWithoutId.stream()
                .map(field -> field.getName() + " = ?")
                .collect(Collectors.joining(", "));

        selectAllSql = String.format("select * from %s", name);

        selectByIdSql = String.format("select * from %s where %s = ?", name, id);

        insertSql = String.format("insert into %s (%s) values (%s)", name, insertParams, insertValues);

        updateSql = String.format("update %s set %s where %s = ?", name, updateParams, id);
    }

    @Override
    public String getSelectAllSql() {
        return selectAllSql;
    }

    @Override
    public String getSelectByIdSql() {
        return selectByIdSql;
    }

    @Override
    public String getInsertSql() {
        return insertSql;
    }

    @Override
    public String getUpdateSql() {
        return updateSql;
    }
}
