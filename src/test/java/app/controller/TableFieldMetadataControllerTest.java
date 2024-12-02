package app.controller;

import app.advice.DefaultAdvice;
import app.exception.DataNotFoundException;
import app.model.ErrorResponse;
import app.model.TableFieldMetadataDto;
import app.service.TableFieldMetadataService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static app.utils.TestUtils.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import({TableFieldMetadataController.class, DefaultAdvice.class})
@AutoConfigureMockMvc
@EnableAutoConfiguration
public class TableFieldMetadataControllerTest {
    static String URL_PREFIX;
    static TableFieldMetadataDto dto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TableFieldMetadataService tableFieldMetadataService;

    @BeforeAll
    static void init() {
        URL_PREFIX = "/fields";
        dto = getTableFieldMetadataDto();
    }

    @Test
    void getListTableFieldsOfTheTypeInSchemaTest() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheTypeInSchema(anyString(), anyString()))
                .thenReturn(List.of(dto));

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL_PREFIX + "/by-data-type-in-schema")
                        .content(objectMapper.writeValueAsString(getByDataTypeInSchemaRequest()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TableFieldMetadataDto> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<List<TableFieldMetadataDto>>(){}
        );
        checkResult(dto, result);
    }

    @Test
    void getListTableFieldsOfTheTypeInSchema404Test() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheTypeInSchema(anyString(), anyString()))
                .thenThrow(new DataNotFoundException("Поля типа test_column в схеме test_schema не найдены"));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-data-type-in-schema")
                                .content(objectMapper.writeValueAsString(getByDataTypeInSchemaRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<ErrorResponse>(){}
        );
        checkResult(result, "Поля типа test_column в схеме test_schema не найдены");
    }

    @Test
    void getListTableFieldsOfTheNameInDatabaseSchemaTest() throws Exception {
        Mockito.when(tableFieldMetadataService
                        .getListTableFieldsOfTheNameInDatabaseSchema(anyString(), anyString(), anyString()))
                .thenReturn(List.of(dto));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-name-in-database-schema")
                                .content(objectMapper.writeValueAsString(getByNameInDatabaseSchemaRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TableFieldMetadataDto> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<List<TableFieldMetadataDto>>() {
                }
        );
        checkResult(dto, result);
    }

    @Test
    void getListTableFieldsOfTheNameInDatabaseSchema404Test() throws Exception {
        Mockito.when(tableFieldMetadataService
                        .getListTableFieldsOfTheNameInDatabaseSchema(anyString(), anyString(), anyString()))
                .thenThrow(new DataNotFoundException(
                        "Поля с именем test_column в схеме test_schema базы данных test_database не найдены"
                ));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-name-in-database-schema")
                                .content(objectMapper.writeValueAsString(getByNameInDatabaseSchemaRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<ErrorResponse>() {
                }
        );
        checkResult(result, "Поля с именем test_column в схеме test_schema базы данных test_database не найдены");
    }

    @Test
    void getListTableFieldsOfTheDatabaseTest() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheDatabase(anyString())).thenReturn(List.of(dto));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-database")
                                .content(objectMapper.writeValueAsString(getByDatabaseRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TableFieldMetadataDto> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<List<TableFieldMetadataDto>>() {
                }
        );
        checkResult(dto, result);
    }

    @Test
    void getListTableFieldsOfTheDatabase404Test() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheDatabase(anyString()))
                .thenThrow(new DataNotFoundException("База данных test_database не найдена"));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-database")
                                .content(objectMapper.writeValueAsString(getByDatabaseRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<ErrorResponse>() {
                }
        );
        checkResult(result, "База данных test_database не найдена");
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseTest() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheTableInDatabase(anyString(), anyString()))
                .thenReturn(List.of(dto));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-table-in-database")
                                .content(objectMapper.writeValueAsString(getByTableInDatabaseRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TableFieldMetadataDto> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<List<TableFieldMetadataDto>>() {
                }
        );
        checkResult(dto, result);
    }

    @Test
    void getListTableFieldsOfTheTableInDatabase404Test() throws Exception {
        Mockito.when(tableFieldMetadataService.getListTableFieldsOfTheTableInDatabase(anyString(), anyString()))
                .thenThrow(new DataNotFoundException("База данных test_database или таблица test_table не найдена"));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-table-in-database")
                                .content(objectMapper.writeValueAsString(getByTableInDatabaseRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<ErrorResponse>() {
                }
        );
        checkResult(result, "База данных test_database или таблица test_table не найдена");
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseSchemaTest() throws Exception {
        Mockito.when(tableFieldMetadataService
                        .getListTableFieldsOfTheNameInDatabaseSchema(anyString(), anyString(), anyString()))
                .thenReturn(List.of(dto));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-table-in-database-schema")
                                .content(objectMapper.writeValueAsString(getByTableInDatabaseSchemaRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<TableFieldMetadataDto> result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<List<TableFieldMetadataDto>>() {
                }
        );
        checkResult(dto, result);
    }

    @Test
    void getListTableFieldsOfTheTableInDatabaseSchema404Test() throws Exception {
        Mockito.when(tableFieldMetadataService
                        .getListTableFieldsOfTheTableInDatabaseSchema(anyString(), anyString(), anyString()))
                .thenThrow(new DataNotFoundException(
                        "База данных test_database, схема test_schema или таблица test_table не найдена"
                ));

        MvcResult mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post(URL_PREFIX + "/by-table-in-database-schema")
                                .content(objectMapper.writeValueAsString(getByTableInDatabaseSchemaRequest()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

        ErrorResponse result = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(), new TypeReference<ErrorResponse>() {
                }
        );
        checkResult(result, "База данных test_database, схема test_schema или таблица test_table не найдена");
    }


}
