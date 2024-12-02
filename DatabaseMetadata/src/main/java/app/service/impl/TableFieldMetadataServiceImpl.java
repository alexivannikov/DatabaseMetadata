package app.service.impl;

import app.exception.DataNotFoundException;
import app.mapper.CommonMapper;
import app.model.TableFieldMetadataDto;
import app.repository.TableFieldMetadataRepository;
import app.service.TableFieldMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableFieldMetadataServiceImpl implements TableFieldMetadataService {

    @Autowired
    private TableFieldMetadataRepository tableFieldMetadataRepository;

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<TableFieldMetadataDto> getListTableFieldsOfTheTypeInSchema(String schemaName,
                                                                           String fieldType) {
        var tableFieldList = tableFieldMetadataRepository.findBySchemaAndDataType(schemaName, fieldType);
        if (tableFieldList.isEmpty()) {
            throw new DataNotFoundException(
                    String.format("Поля типа %s в схеме %s не найдены", fieldType, schemaName)
            );
        }
        return commonMapper.toDtoList(tableFieldList);
    }

    @Override
    public List<TableFieldMetadataDto> getListTableFieldsOfTheNameInDatabaseSchema(String databaseName,
                                                                                   String schemaName,
                                                                                   String fieldName) {
        var tableFieldList = tableFieldMetadataRepository.findByDatabaseAndSchemaAndColumn(
                databaseName, schemaName, fieldName
        );
        if (tableFieldList.isEmpty()) {
            throw new DataNotFoundException(
                    String.format(
                            "Поля с именем %s в схеме %s базы данных %s не найдены",
                            fieldName,
                            schemaName,
                            databaseName
                    )
            );
        }
        return commonMapper.toDtoList(tableFieldList);
    }

    @Override
    public List<TableFieldMetadataDto> getListTableFieldsOfTheDatabase(String databaseName) {
        var tableFieldList = tableFieldMetadataRepository.findByDatabase(databaseName);
        if (tableFieldList.isEmpty()) {
            throw new DataNotFoundException(String.format("База данных %s не найдена", databaseName));
        }
        return commonMapper.toDtoList(tableFieldList);
    }

    @Override
    public List<TableFieldMetadataDto> getListTableFieldsOfTheTableInDatabase(String databaseName, String tableName) {
        var tableFieldList = tableFieldMetadataRepository.findByDatabaseAndTable(databaseName, tableName);
        if (tableFieldList.isEmpty()) {
            throw new DataNotFoundException(
                    String.format("База данных %s или таблица %s не найдена", databaseName, tableName)
            );
        }
        return commonMapper.toDtoList(tableFieldList);
    }

    @Override
    public List<TableFieldMetadataDto> getListTableFieldsOfTheTableInDatabaseSchema(String databaseName,
                                                                                    String schemaName,
                                                                                    String tableName) {
        var tableFieldList = tableFieldMetadataRepository.findByDatabaseAndSchemaAndTable(
                databaseName, schemaName, tableName
        );
        if (tableFieldList.isEmpty()) {
            throw new DataNotFoundException(
                    String.format("База данных %s, схема %s или таблица %s не найдена",
                            databaseName,
                            schemaName,
                            tableName)
            );
        }
        return commonMapper.toDtoList(tableFieldList);
    }
}
