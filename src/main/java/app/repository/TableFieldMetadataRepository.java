package app.repository;

import app.entity.TableFieldMetadata;
import app.entity.TableFieldMetadataId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableFieldMetadataRepository extends JpaRepository<TableFieldMetadata, TableFieldMetadataId> {
    String QUERY_PREFIX = "SELECT " +
            "c.table_catalog AS db_name," +
            "c.table_schema AS schema_name," +
            "c.table_name," +
            "c.column_name," +
            "c.udt_name AS column_type," +
            "obj_description(table_schema\\:\\:regnamespace) AS schema_description," +
            "pg_catalog.shobj_description(d.oid, 'pg_database') AS db_description," +
            "obj_description((table_schema||'.'||table_name)\\:\\:regclass, 'pg_class') AS table_description," +
            "col_description((table_schema||'.'||table_name)\\:\\:regclass\\:\\:oid, ordinal_position) " +
            "AS column_description " +
            "FROM information_schema.columns c " +
            "JOIN pg_catalog.pg_database d ON c.table_catalog = d.datname ";
    String QUERY_POSTFIX = " AND c.table_name NOT LIKE '%databasechangelog%'";

    @Query(value = QUERY_PREFIX + "WHERE c.table_schema = :schema AND c.udt_name LIKE %:dataType%" + QUERY_POSTFIX,
            nativeQuery = true)
    List<TableFieldMetadata> findBySchemaAndDataType(@Param("schema") String schema,
                                                     @Param("dataType") String dataType);

    @Query(value = QUERY_PREFIX +
            "WHERE c.table_catalog = :database AND c.table_schema = :schema AND c.column_name = :column" +
            QUERY_POSTFIX,
            nativeQuery = true)
    List<TableFieldMetadata> findByDatabaseAndSchemaAndColumn(@Param("database") String database,
                                                              @Param("schema") String schema,
                                                              @Param("column") String column);

    @Query(value = QUERY_PREFIX +
            "WHERE c.table_catalog = :database AND c.table_schema NOT IN ('information_schema', 'pg_catalog')" +
            QUERY_POSTFIX,
            nativeQuery = true)
    List<TableFieldMetadata> findByDatabase(@Param("database") String database);

    @Query(value = QUERY_PREFIX + "WHERE c.table_catalog = :database AND c.table_name = :table" + QUERY_POSTFIX,
            nativeQuery = true)
    List<TableFieldMetadata> findByDatabaseAndTable(@Param("database") String database, @Param("table") String table);

    @Query(value = QUERY_PREFIX +
            "WHERE c.table_catalog = :database AND c.table_schema = :schema AND c.table_name = :table" +
            QUERY_POSTFIX,
            nativeQuery = true)
    List<TableFieldMetadata> findByDatabaseAndSchemaAndTable(@Param("database") String database,
                                                             @Param("schema") String schema,
                                                             @Param("table") String table);

}
