package app.service;

import app.model.TableFieldMetadataDto;

import java.util.List;

public interface TableFieldMetadataService {

    List<TableFieldMetadataDto> getListTableFieldsOfTheTypeInSchema(String schemaName, String fieldType);

    List<TableFieldMetadataDto> getListTableFieldsOfTheNameInDatabaseSchema(
            String databaseName, String schemaName, String fieldName
    );

    List<TableFieldMetadataDto> getListTableFieldsOfTheDatabase(String databaseName);

    List<TableFieldMetadataDto> getListTableFieldsOfTheTableInDatabase(String databaseName, String tableName);

    List<TableFieldMetadataDto> getListTableFieldsOfTheTableInDatabaseSchema(
            String databaseName, String schemaName, String tableName
    );
}
