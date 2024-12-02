package app.mapper;

import app.entity.TableFieldMetadata;
import app.model.TableFieldMetadataDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-01T16:44:04+0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommonMapperImpl implements CommonMapper {

    @Override
    public TableFieldMetadataDto toDto(TableFieldMetadata source) {
        if ( source == null ) {
            return null;
        }

        TableFieldMetadataDto tableFieldMetadataDto = new TableFieldMetadataDto();

        tableFieldMetadataDto.setDbName( source.getDbName() );
        tableFieldMetadataDto.setDbDescription( source.getDbDescription() );
        tableFieldMetadataDto.setSchemaDescription( source.getSchemaDescription() );
        tableFieldMetadataDto.setTableDescription( source.getTableDescription() );
        tableFieldMetadataDto.setColumnDescription( source.getColumnDescription() );
        tableFieldMetadataDto.setColumnType( source.getColumnType() );

        tableFieldMetadataDto.setSchemaName( source.getId().getSchemaName() );
        tableFieldMetadataDto.setTableName( source.getId().getTableName() );
        tableFieldMetadataDto.setColumnName( source.getId().getColumnName() );

        return tableFieldMetadataDto;
    }

    @Override
    public List<TableFieldMetadataDto> toDtoList(List<TableFieldMetadata> tableFieldMetadata) {
        if ( tableFieldMetadata == null ) {
            return null;
        }

        List<TableFieldMetadataDto> list = new ArrayList<TableFieldMetadataDto>( tableFieldMetadata.size() );
        for ( TableFieldMetadata tableFieldMetadata1 : tableFieldMetadata ) {
            list.add( toDto( tableFieldMetadata1 ) );
        }

        return list;
    }
}
