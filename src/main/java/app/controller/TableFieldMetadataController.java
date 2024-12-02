package app.controller;

import app.model.*;
import app.service.TableFieldMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fields")
public class TableFieldMetadataController {

    @Autowired
    private TableFieldMetadataService tableFieldMetadataService;

    @PostMapping("/by-data-type-in-schema")
    public ResponseEntity<List<TableFieldMetadataDto>> getListTableFieldsOfTheTypeInSchema(
            @RequestBody ByDataTypeInSchemaRequest request
    ) {
        return ResponseEntity.ok(
                tableFieldMetadataService.getListTableFieldsOfTheTypeInSchema(
                        request.getSchemaName(), request.getFieldType()
                )
        );
    }

    @PostMapping("/by-name-in-database-schema")
    public ResponseEntity<List<TableFieldMetadataDto>> getListTableFieldsOfTheNameInDatabaseSchema(
            @RequestBody ByNameInDatabaseSchemaRequest request
    ) {
        return ResponseEntity.ok(
                tableFieldMetadataService.getListTableFieldsOfTheNameInDatabaseSchema(
                        request.getDatabaseName(), request.getSchemaName(), request.getFieldName()
                )
        );
    }

    @PostMapping("/by-database")
    public ResponseEntity<List<TableFieldMetadataDto>> getListTableFieldsOfTheDatabase(
            @RequestBody ByDatabaseRequest request
    ) {
        return ResponseEntity.ok(tableFieldMetadataService.getListTableFieldsOfTheDatabase(request.getDatabaseName()));
    }

    @PostMapping("/by-table-in-database")
    public ResponseEntity<List<TableFieldMetadataDto>> getListTableFieldsOfTheDatabase(
            @RequestBody ByTableInDatabaseRequest request
    ) {
        return ResponseEntity.ok(
                tableFieldMetadataService.getListTableFieldsOfTheTableInDatabase(
                        request.getDatabaseName(),
                        request.getTableName()
                )
        );
    }

    @PostMapping("/by-table-in-database-schema")
    public ResponseEntity<List<TableFieldMetadataDto>> getListTableFieldsOfTheDatabaseSchema(
            @RequestBody ByTableInDatabaseSchemaRequest request
    ) {
        return ResponseEntity.ok(
                tableFieldMetadataService.getListTableFieldsOfTheTableInDatabaseSchema(
                        request.getDatabaseName(),
                        request.getSchemaName(),
                        request.getTableName()
                )
        );
    }
}
