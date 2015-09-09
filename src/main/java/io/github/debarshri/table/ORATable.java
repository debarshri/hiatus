package io.github.debarshri.table;

import java.util.List;

public class ORATable {
    private List<String> columns;
    private List<String> pk;
    private String tableName;


    public ORATable() {
    }

    public ORATable(String target) {

        this.tableName = target;
    }

    public String getTableName() {
        return tableName;
    }
}
