package app.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(schema = "information_schema", name = "columns")
public class TableFieldMetadata implements Serializable {

    @EmbeddedId
    private TableFieldMetadataId id;
    private String tableDescription;
    private String dbName;
    private String dbDescription;
    private String schemaDescription;
    private String columnDescription;
    private String columnType;

    public TableFieldMetadataId getId() {
        return id;
    }

    public void setId(TableFieldMetadataId id) {
        this.id = id;
    }

    public String getTableDescription() {
        return tableDescription;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbDescription() {
        return dbDescription;
    }

    public String getSchemaDescription() {
        return schemaDescription;
    }

    public String getColumnDescription() {
        return columnDescription;
    }

    public String getColumnType() {
        return columnType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TableFieldMetadata that = (TableFieldMetadata) o;
        return id.getSchemaName().equals(that.getId().getSchemaName()) &&
                id.getTableName().equals(that.getId().getTableName()) &&
                id.getColumnName().equals(that.getId().getColumnName());
    }

    @Override
    public int hashCode() {
        return 31 * (id.getSchemaName().hashCode() + id.getTableName().hashCode() + id.getColumnName().hashCode());
    }
}
