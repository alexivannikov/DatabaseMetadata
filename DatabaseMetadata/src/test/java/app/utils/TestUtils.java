package app.utils;

import app.model.*;

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
}
