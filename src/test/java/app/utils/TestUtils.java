package app.utils;

import app.entity.TableFieldMetadata;
import app.entity.TableFieldMetadataId;
import app.model.*;
import org.junit.jupiter.api.Assertions;
import org.springframework.http.HttpStatus;

import java.util.List;

public class TestUtils {
    public static ByDataTypeInSchemaRequest getByDataTypeInSchemaRequest() {
        ByDataTypeInSchemaRequest request = new ByDataTypeInSchemaRequest();
        request.setSchemaName("test_schema");
        request.setFieldType("varchar");
        return request;
    }

    public static ByNameInDatabaseSchemaRequest getByNameInDatabaseSchemaRequest() {
        ByNameInDatabaseSchemaRequest request = new ByNameInDatabaseSchemaRequest();
        request.setDatabaseName("test_database");
        request.setSchemaName("test_schema");
        request.setFieldName("test_column");
        return request;
    }

    public static ByDatabaseRequest getByDatabaseRequest() {
        ByDatabaseRequest request = new ByDatabaseRequest();
        request.setDatabaseName("test_database");
        return request;
    }

    public static ByTableInDatabaseRequest getByTableInDatabaseRequest() {
        ByTableInDatabaseRequest request = new ByTableInDatabaseRequest();
        request.setDatabaseName("test_database");
        request.setTableName("test_table");
        return request;
    }

    public static ByTableInDatabaseSchemaRequest getByTableInDatabaseSchemaRequest() {
        ByTableInDatabaseSchemaRequest request = new ByTableInDatabaseSchemaRequest();
        request.setDatabaseName("test_database");
        request.setSchemaName("test_schema");
        request.setTableName("test_table");
        return request;
    }

    public static TableFieldMetadata getTableFieldMetadata(String schemaName, String tableName, String columnName) {
        TableFieldMetadataId id = new TableFieldMetadataId();
        id.setSchemaName(schemaName);
        id.setTableName(tableName);
        id.setColumnName(columnName);
        TableFieldMetadata tableFieldMetadata = new TableFieldMetadata();
        tableFieldMetadata.setId(id);
        return tableFieldMetadata;
    }

    public static TableFieldMetadataDto getTableFieldMetadataDto() {
        TableFieldMetadataDto dto = new TableFieldMetadataDto();
        dto.setDbName("test_database");
        dto.setDbDescription("Test database description");
        dto.setSchemaName("test_schema");
        dto.setSchemaDescription("Test schema description");
        dto.setTableName("test_table");
        dto.setTableDescription("Test table description");
        dto.setColumnName("test_column");
        dto.setColumnDescription("Test column description");
        dto.setColumnType("varchar");
        return dto;
    }

    public static void checkResult(List<TableFieldMetadata> expected, List<TableFieldMetadataDto> actual) {
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.size(), actual.size());
        int index = 0;
        for (TableFieldMetadataDto dto : actual) {
            Assertions.assertEquals(expected.get(index).getId().getSchemaName(), dto.getSchemaName());
            Assertions.assertEquals(expected.get(index).getId().getTableName(), dto.getTableName());
            Assertions.assertEquals(expected.get(index).getId().getColumnName(), dto.getColumnName());
            index++;
        }
    }

    public static void checkResult(TableFieldMetadataDto expected, List<TableFieldMetadataDto> actual) {
        Assertions.assertNotNull(actual);
        actual.stream().findFirst().ifPresent(it -> {
                    Assertions.assertEquals(expected.getDbName(), it.getDbName());
                    Assertions.assertEquals(expected.getDbDescription(), it.getDbDescription());
                    Assertions.assertEquals(expected.getSchemaName(), it.getSchemaName());
                    Assertions.assertEquals(expected.getSchemaDescription(), it.getSchemaDescription());
                    Assertions.assertEquals(expected.getTableName(), it.getTableName());
                    Assertions.assertEquals(expected.getTableDescription(), it.getTableDescription());
                    Assertions.assertEquals(expected.getColumnName(), it.getColumnName());
                    Assertions.assertEquals(expected.getColumnDescription(), it.getColumnDescription());
                    Assertions.assertEquals(expected.getColumnType(), it.getColumnType());
                }
        );
    }

    public static void checkResult(ErrorResponse error, String errorMessage) {
        Assertions.assertEquals(HttpStatus.NOT_FOUND, error.getStatus());
        Assertions.assertEquals(Integer.valueOf(404), error.getCode());
        Assertions.assertEquals(errorMessage, error.getMessage());
    }
}
