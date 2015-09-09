package io.github.debarshri.table;

public class HBaseTable {
    public String getSource() {
        return source;
    }

    public HBaseTable() {
    }

    private String source;

    public HBaseTable(String source) {
        this.source = source;
    }
}
