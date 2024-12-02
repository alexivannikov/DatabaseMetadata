package app.mapper;

import app.entity.TableFieldMetadata;
import app.model.TableFieldMetadataDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommonMapper {
    @Mapping(target = "schemaName", expression = "java(source.getId().getSchemaName())")
    @Mapping(target = "tableName", expression = "java(source.getId().getTableName())")
    @Mapping(target = "columnName", expression = "java(source.getId().getColumnName())")
    TableFieldMetadataDto toDto(TableFieldMetadata source);

    List<TableFieldMetadataDto> toDtoList(List<TableFieldMetadata> tableFieldMetadata);
}
