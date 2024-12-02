package app.service;

import app.entity.TableFieldMetadata;
import app.exception.DataNotFoundException;
import app.mapper.CommonMapper;
import app.model.TableFieldMetadataDto;
import app.repository.TableFieldMetadataRepository;
import app.service.impl.TableFieldMetadataServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static app.utils.TestUtils.checkResult;
import static app.utils.TestUtils.getTableFieldMetadata;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TableFieldMetadataServiceTest {
    @Spy
    static CommonMapper commonMapper;

    @Mock
    TableFieldMetadataRepository tableFieldMetadataRepository;

    @InjectMocks
    TableFieldMetadataServiceImpl tableFieldMetadataService;

    @BeforeAll
    static void init() {
        commonMapper = Mappers.getMapper(CommonMapper.class);
    }

    @Test
    void getListTableFieldsOfTheTypeInSchemaTest() {
        List<TableFieldMetadata> tableFieldMetadataList = List.of(
                getTableFieldMetadata("test_schema", "first_table", "test_column"),
                getTableFieldMetadata("test_schema", "second_table", "test_column")
        );
        Mockito.when(tableFieldMetadataRepository.findBySchemaAndDataType("test_schema", "test_column"))
                .thenReturn(tableFieldMetadataList);

        List<TableFieldMetadataDto> result =
                tableFieldMetadataService.getListTableFieldsOfTheTypeInSchema("test_schema", "test_column");

        checkResult(tableFieldMetadataList, result);
    }

    @Test
    void getListTableFieldsOfTheTypeInSchemaFailTest() {
        Mockito.when(tableFieldMetadataRepository.findBySchemaAndDataType("test_schema", "test_column"))
                .thenReturn(Collections.emptyList());

        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> tableFieldMetadataService.getListTableFieldsOfTheTypeInSchema("test_schema", "test_column")
        );

        Assertions.assertEquals("Поля типа test_column в схеме test_schema не найдены", ex.getMessage());
    }

    @Test
    void getListTableFieldsOfTheNameInDatabaseSchemaTest() {
        List<TableFieldMetadata> tableFieldMetadataList = List.of(
                getTableFieldMetadata("test_schema", "first_table", "test_column"),
                getTableFieldMetadata("test_schema", "second_table", "test_column")
        );
        Mockito.when(tableFieldMetadataRepository
                .findByDatabaseAndSchemaAndColumn("test_database", "test_schema", "test_column")
        ).thenReturn(tableFieldMetadataList);

        List<TableFieldMetadataDto> result = tableFieldMetadataService
                .getListTableFieldsOfTheNameInDatabaseSchema("test_database", "test_schema", "test_column");

        checkResult(tableFieldMetadataList, result);
    }

    @Test
    void getListTableFieldsOfTheNameInDatabaseSchemaFailTest() {
        Mockito.when(tableFieldMetadataRepository
                .findByDatabaseAndSchemaAndColumn("test_database", "test_schema", "test_column")
        ).thenReturn(Collections.emptyList());

        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> tableFieldMetadataService.getListTableFieldsOfTheNameInDatabaseSchema(
                        "test_database", "test_schema", "test_column"
                )
        );

        Assertions.assertEquals(
                "Поля с именем test_column в схеме test_schema базы данных test_database не найдены", ex.getMessage()
        );
    }

    @Test
    void getListTableFieldsOfTheDatabaseTest() {
        List<TableFieldMetadata> tableFieldMetadataList = List.of(
                getTableFieldMetadata("first_schema", "first_table", "first_column"),
                getTableFieldMetadata("second_schema", "second_table", "second_column")
        );
        Mockito.when(tableFieldMetadataRepository.findByDatabase("test_database")).thenReturn(tableFieldMetadataList);

        List<TableFieldMetadataDto> result = tableFieldMetadataService.getListTableFieldsOfTheDatabase("test_database");

        checkResult(tableFieldMetadataList, result);
    }

    @Test
    void getListTableFieldsOfTheDatabaseFailTest() {
        Mockito.when(tableFieldMetadataRepository.findByDatabase("test_database")).thenReturn(Collections.emptyList());

        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> tableFieldMetadataService.getListTableFieldsOfTheDatabase("test_database")
        );

        Assertions.assertEquals("База данных test_database не найдена", ex.getMessage());
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseTest() {
        List<TableFieldMetadata> tableFieldMetadataList = List.of(
                getTableFieldMetadata("first_schema", "test_table", "first_column"),
                getTableFieldMetadata("second_schema", "test_table", "second_column")
        );
        Mockito.when(tableFieldMetadataRepository.findByDatabaseAndTable("test_database", "test_table"))
                .thenReturn(tableFieldMetadataList);

        List<TableFieldMetadataDto> result =
                tableFieldMetadataService.getListTableFieldsOfTheTableInDatabase("test_database", "test_table");

        checkResult(tableFieldMetadataList, result);
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseTestFailTest() {
        Mockito.when(tableFieldMetadataRepository.findByDatabaseAndTable("test_database", "test_table"))
                .thenReturn(Collections.emptyList());

        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> tableFieldMetadataService.getListTableFieldsOfTheTableInDatabase("test_database", "test_table")
        );

        Assertions.assertEquals("База данных test_database или таблица test_table не найдена", ex.getMessage());
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseSchemaTest() {
        List<TableFieldMetadata> tableFieldMetadataList = List.of(
                getTableFieldMetadata("first_schema", "first_table", "first_column"),
                getTableFieldMetadata("second_schema", "second_table", "second_column")
        );
        Mockito.when(tableFieldMetadataRepository
                .findByDatabaseAndSchemaAndTable("test_database", "test_schema", "test_table")
        ).thenReturn(tableFieldMetadataList);

        List<TableFieldMetadataDto> result = tableFieldMetadataService
                .getListTableFieldsOfTheTableInDatabaseSchema("test_database", "test_schema", "test_table");

        checkResult(tableFieldMetadataList, result);
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseSchemaFailTest() {
        Mockito.when(tableFieldMetadataRepository
                .findByDatabaseAndSchemaAndTable("test_database", "test_schema", "test_table")
        ).thenReturn(Collections.emptyList());

        DataNotFoundException ex = assertThrows(
                DataNotFoundException.class,
                () -> tableFieldMetadataService.getListTableFieldsOfTheTableInDatabaseSchema(
                        "test_database", "test_schema", "test_table"
                )
        );

        Assertions.assertEquals(
                "База данных test_database, схема test_schema или таблица test_table не найдена", ex.getMessage()
        );
    }
}
